# 谷粒商城



### 概览

谷粒商城是一个微服务项目，总体上分为前台购物模块和后台数据管理模块。



基于 `SpringCloud` +` SpringCloudAlibaba` + `MyBatis-Plus`(数据持久层) + `Redis` (缓存)+ `SpringSession `+ `RabbitMQ` + `Nginx` + `ES` 实现，`Nginx` 实现反向代理和动静分离，采用 `Docker` 容器化部署。



前台商城系统包括：用户登录、注册、商品搜索、商品详情、购物车、下订单流程、秒杀活动等模块。后台管理系统包括：系统管理、商品系统、优惠营销、库存系统、订单系统、用户系统、内容管理等七大模块。



***本项目的`MySQL`、`Redis`、`Nacos`目前部署在虚拟机的`docker`容器中，通过`Nacos`进行统一配置管理***



`Nacos`**终端**`Url`: `http://[虚拟机ip地址]/nacos`



##### **当前的项目整体结构**

![](C:\Users\Losgai\AppData\Roaming\marktext\images\2024-12-30-11-58-31-image.png)

##### **开发进度**

*后台系统开发中...*

`gulimall-coupon`

优惠系统模块，暂未开始开发

优惠营销主要包括满减折扣, 秒杀活动等。秒杀活动可以配置一次秒杀活动, 并且配置这次活动中的多个秒杀场次, 并且配置每个场次关联的商品



`gulimall-gateway`

网关, 定义路由规则



`gulimall-member`

会员信息管理，目前做好了会员列表管理和会员等级定义



`gulimall-order`

订单系统，还未开始开发



`gulimall-product`

商品系统，后台系统的基本功能已完成, 包括品牌与分类管理, `sku`/`spu`管理, 销售属性与销售规格管理, 和后台商品发布流程



`gulimall-third-party`

第三方服务模块, 目前接入了阿里云`OSS`, 通过`access-key`和`secret-key`使用`OSS`服务储存图片



`gulimall-ware`

库存管理服务, 目前完成了后台系统的采购单维护和合并整单的功能



`renren-security`

整合了后台系统的安全服务, 包括整个后台系统的基本服务, 代码生成器, 枚举类, 配置类, 和通用工具类等 (人人开源框架二次开发)


