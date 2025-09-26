package com.losgai.gulimall.product.utils;

import org.springframework.cache.support.NullValue;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.data.redis.cache.CacheStatistics;
import org.springframework.data.redis.cache.CacheStatisticsCollector;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStringCommands.SetOption;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;

public class RandomRedisCacheWriter implements RedisCacheWriter {
    private final RedisConnectionFactory connectionFactory;
    private final Duration sleepTime;
    private final CacheStatisticsCollector statistics;
    private double origin = 0.9;
    private double bound = 1.1;

    private static final byte[] BINARY_NULL_VALUE;

    static {
        BINARY_NULL_VALUE = RedisSerializer.java().serialize(NullValue.INSTANCE);
    }

    public RandomRedisCacheWriter(RedisConnectionFactory connectionFactory) {
        this(connectionFactory, Duration.ZERO);
    }

    public RandomRedisCacheWriter(RedisConnectionFactory connectionFactory, double origin, double bound) {
        this(connectionFactory, Duration.ZERO);
        this.origin = origin;
        this.bound = bound;
    }

    RandomRedisCacheWriter(RedisConnectionFactory connectionFactory, Duration sleepTime) {
        this(connectionFactory, sleepTime, CacheStatisticsCollector.none());
    }

    RandomRedisCacheWriter(RedisConnectionFactory connectionFactory, Duration sleepTime, CacheStatisticsCollector cacheStatisticsCollector) {
        Assert.notNull(connectionFactory, "ConnectionFactory must not be null!");
        Assert.notNull(sleepTime, "SleepTime must not be null!");
        Assert.notNull(cacheStatisticsCollector, "CacheStatisticsCollector must not be null!");
        this.connectionFactory = connectionFactory;
        this.sleepTime = sleepTime;
        this.statistics = cacheStatisticsCollector;
    }

    public void put(String name, byte[] key, byte[] value, @Nullable Duration ttl) {
        Assert.notNull(name, "Name must not be null!");
        Assert.notNull(key, "Key must not be null!");
        Assert.notNull(value, "Value must not be null!");
        this.execute(name, (connection) -> {
            if (Arrays.equals(BINARY_NULL_VALUE, value)) { // 判断下如果是空值超时时间设置短点
                connection.set(key, value, Expiration.from(Duration.ofSeconds(3).toMillis(), TimeUnit.MILLISECONDS), SetOption.upsert());
                return "OK";
            }
            if (shouldExpireWithin(ttl)) {
                // 添加随机数
                // 生成一个在 设定系数区间内的随机乘数
                double randomFactor = ThreadLocalRandom.current().nextDouble(
                        origin,
                        bound
                );
                long originalMillis = ttl.toMillis();
                long randomizedMillis = (long) (originalMillis * randomFactor);
                Duration ttl2 = Duration.ofMillis(randomizedMillis);
                connection.set(key, value, Expiration.from(ttl2.toMillis(), TimeUnit.MILLISECONDS), SetOption.upsert());
            } else {
                connection.set(key, value);
            }

            return "OK";
        });
        this.statistics.incPuts(name);
    }

    @Override
    public CompletableFuture<Void> store(String name, byte[] key, byte[] value, Duration ttl) {
        return null;
    }

    public byte[] get(String name, byte[] key) {
        Assert.notNull(name, "Name must not be null!");
        Assert.notNull(key, "Key must not be null!");
        byte[] result = (byte[]) this.execute(name, (connection) -> {
            return connection.get(key);
        });
        this.statistics.incGets(name);
        if (result != null) {
            this.statistics.incHits(name);
        } else {
            this.statistics.incMisses(name);
        }

        return result;
    }

    @Override
    public CompletableFuture<byte[]> retrieve(String name, byte[] key, Duration ttl) {
        return null;
    }

    public byte[] putIfAbsent(String name, byte[] key, byte[] value, @Nullable Duration ttl) {
        Assert.notNull(name, "Name must not be null!");
        Assert.notNull(key, "Key must not be null!");
        Assert.notNull(value, "Value must not be null!");
        return (byte[]) this.execute(name, (connection) -> {
            if (this.isLockingCacheWriter()) {
                this.doLock(name, connection);
            }

            Object var7;
            try {
                boolean put;
                if (Arrays.equals(BINARY_NULL_VALUE, value)) {
                    put = connection.set(key, value, Expiration.from(Duration.ofSeconds(3).toMillis(), TimeUnit.MILLISECONDS), SetOption.upsert());
                } else {
                    if (shouldExpireWithin(ttl)) {
                        // 添加随机数
                        // 生成一个在 设定系数区间内的随机乘数
                        double randomFactor = ThreadLocalRandom.current().nextDouble(
                                origin,
                                bound
                        );
                        long originalMillis = ttl.toMillis();
                        long randomizedMillis = (long) (originalMillis * randomFactor);
                        Duration ttl2 = Duration.ofMillis(randomizedMillis);
                        put = connection.set(key, value, Expiration.from(ttl2), SetOption.ifAbsent());
                    } else {
                        put = connection.setNX(key, value);
                    }
                }

                if (!put) {
                    byte[] var11 = connection.get(key);
                    return var11;
                }

                this.statistics.incPuts(name);
                var7 = null;
            } finally {
                if (this.isLockingCacheWriter()) {
                    this.doUnlock(name, connection);
                }

            }

            return (byte[]) var7;
        });
    }

    public void remove(String name, byte[] key) {
        Assert.notNull(name, "Name must not be null!");
        Assert.notNull(key, "Key must not be null!");
        this.execute(name, (connection) -> {
            return connection.del(new byte[][]{key});
        });
        this.statistics.incDeletes(name);
    }

    public void clean(String name, byte[] pattern) {
        Assert.notNull(name, "Name must not be null!");
        Assert.notNull(pattern, "Pattern must not be null!");
        this.execute(name, (connection) -> {
            boolean wasLocked = false;

            try {
                if (this.isLockingCacheWriter()) {
                    this.doLock(name, connection);
                    wasLocked = true;
                }

                byte[][] keys = (byte[][]) ((Set) Optional.ofNullable(connection.keys(pattern)).orElse(Collections.emptySet())).toArray(new byte[0][]);
                if (keys.length > 0) {
                    this.statistics.incDeletesBy(name, keys.length);
                    connection.del(keys);
                }
            } finally {
                if (wasLocked && this.isLockingCacheWriter()) {
                    this.doUnlock(name, connection);
                }

            }

            return "OK";
        });
    }

    public CacheStatistics getCacheStatistics(String cacheName) {
        return this.statistics.getCacheStatistics(cacheName);
    }

    public void clearStatistics(String name) {
        this.statistics.reset(name);
    }

    public RedisCacheWriter withStatisticsCollector(CacheStatisticsCollector cacheStatisticsCollector) {
        return new RandomRedisCacheWriter(this.connectionFactory, this.sleepTime, cacheStatisticsCollector);
    }

    void lock(String name) {
        this.execute(name, (connection) -> {
            return this.doLock(name, connection);
        });
    }

    void unlock(String name) {
        this.executeLockFree((connection) -> {
            this.doUnlock(name, connection);
        });
    }

    private Boolean doLock(String name, RedisConnection connection) {
        return connection.setNX(createCacheLockKey(name), new byte[0]);
    }

    private Long doUnlock(String name, RedisConnection connection) {
        return connection.del(new byte[][]{createCacheLockKey(name)});
    }

    boolean doCheckLock(String name, RedisConnection connection) {
        return connection.exists(createCacheLockKey(name));
    }

    private boolean isLockingCacheWriter() {
        return !this.sleepTime.isZero() && !this.sleepTime.isNegative();
    }

    private <T> T execute(String name, Function<RedisConnection, T> callback) {
        RedisConnection connection = this.connectionFactory.getConnection();

        Object var4;
        try {
            this.checkAndPotentiallyWaitUntilUnlocked(name, connection);
            var4 = callback.apply(connection);
        } finally {
            connection.close();
        }

        return (T) var4;
    }

    private void executeLockFree(Consumer<RedisConnection> callback) {
        RedisConnection connection = this.connectionFactory.getConnection();

        try {
            callback.accept(connection);
        } finally {
            connection.close();
        }

    }

    private void checkAndPotentiallyWaitUntilUnlocked(String name, RedisConnection connection) {
        if (this.isLockingCacheWriter()) {
            long lockWaitTimeNs = System.nanoTime();

            try {
                while (this.doCheckLock(name, connection)) {
                    Thread.sleep(this.sleepTime.toMillis());
                }
            } catch (InterruptedException var9) {
                Thread.currentThread().interrupt();
                throw new PessimisticLockingFailureException(String.format("Interrupted while waiting to unlock cache %s", name), var9);
            } finally {
                this.statistics.incLockTime(name, System.nanoTime() - lockWaitTimeNs);
            }

        }
    }

    private static boolean shouldExpireWithin(@Nullable Duration ttl) {
        return ttl != null && !ttl.isZero() && !ttl.isNegative();
    }

    private static byte[] createCacheLockKey(String name) {
        return (name + "~lock").getBytes(StandardCharsets.UTF_8);
    }
}

