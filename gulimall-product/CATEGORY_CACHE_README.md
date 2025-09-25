# GuliMall商品分类缓存功能说明

## 功能概述
为GuliMall项目的商品分类数据添加了Redis缓存功能，提高系统性能，减少数据库访问压力。

## 实现细节

### 1. 缓存策略
- **缓存Key**: `product:category:tree`
- **缓存过期时间**: 1天
- **缓存更新策略**: Cache-Aside模式（旁路缓存）
  - 读取时：先查缓存，缓存没有则查数据库并写入缓存
  - 更新时：先更新数据库，然后删除缓存

### 2. 主要修改文件

#### CategoryServiceImpl.java
- 添加了Redis相关依赖注入（StringRedisTemplate、ObjectMapper）
- 修改了`listWithTree()`方法，实现缓存读取逻辑
- 重写了`save()`、`update()`、`delete()`方法，在数据变更后清除缓存
- 修改了`doBatchUpdate()`和`removeMenus()`方法，添加缓存清除逻辑
- 新增`clearCategoryCache()`私有方法用于清除缓存

#### RedisConfig.java（新增）
- 配置ObjectMapper用于JSON序列化
- 配置RedisTemplate设置序列化方式

#### application.yml
- 添加Redis连接配置
- 配置连接池参数

### 3. 缓存流程

#### 读取流程
1. 调用`listWithTree()`方法
2. 首先尝试从Redis缓存获取数据
3. 如果缓存存在，直接返回缓存数据
4. 如果缓存不存在，从数据库查询
5. 将查询结果写入缓存（设置过期时间1天）
6. 返回数据

#### 更新流程
1. 执行数据库更新操作（新增/修改/删除）
2. 清除Redis缓存
3. 下次查询时会重新从数据库获取最新数据并缓存

### 4. 依赖配置
项目已包含Redis依赖：
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

### 5. Redis配置参数
- **主机**: 192.168.200.132
- **端口**: 6389
- **连接超时**: 6000ms
- **最大连接数**: 8
- **最大空闲连接**: 8
- **最小空闲连接**: 0

### 6. 测试类
创建了`CategoryCacheTest.java`测试类，包含以下测试：
- 测试第一次获取数据（从数据库获取并缓存）
- 测试从缓存获取数据
- 测试缓存过期时间设置

### 7. 注意事项
1. 确保Redis服务正常运行
2. 根据实际环境修改Redis连接配置
3. 缓存过期时间可根据业务需求调整
4. 在高并发场景下，可能需要考虑缓存击穿、缓存穿透等问题的处理

### 8. 性能优势
- 减少数据库查询次数
- 提高接口响应速度
- 降低系统负载
- 支持高并发访问

### 9. 后续优化建议
1. 可以考虑使用分布式锁防止缓存击穿
2. 可以添加缓存预热功能
3. 可以实现多级缓存（本地缓存+Redis）
4. 可以根据分类更新频率动态调整缓存过期时间