# 架构说明

**@author: ** ychost<ychost@outlook.com>

**@date:**       2017-07-11

**@version:**  0.1.0

[TOC]

### 目录结构

server
│   │               ├── config                #配置目录
│   │               │   ├── apidoc            	 # api 文档生成，这里主要使用了 swagger2
│   │               │   ├── database    	# 数据库配置
│   │               │   │   └── sql          		# sql 型数据库配置
│   │               │   ├── server         	# 服务器配置，配置路由，安全等等
│   │               │   └── shred          	# 其它的一些公用配置
│   │               ├── controller        # 控制器 (@RestController)
│   │               ├── core                 # 核心层 (贯穿应用程序的 服务)
│   │               ├── manager       	# 管理层 (与数据库交互)   
│   │               ├── model		# 模型层
│   │               │   ├── request		# 前端请求
│   │               │   ├── response	       # 后台回复
│   │               │   └── entity		# 数据库专用的
│   │               └── service		#服务层

### 层级划分

* 主要分层：类似于三层架构
  1. Controller (组装路由与服务)
  2. Service      (业务逻辑处理)
  3. Manager   (数据库管理)
* 附加结构
  1. Config (配置路由，数据库，api文档生成，安全等等)
  2. Core (贯穿全局的服务，如：用户认证，邮箱，短信等服务等等)
  3. Model (与前端交互的模型，数据库实体模型等等)
  4. Util (一些公用的静态方法，如：加解密算法，日期工具转换等等)

### 配置文件

```java
spring.profiles.active = dev 
```

激活的是 dev 配置，目前只对数据库进行了配置

```java
server.port=9004
server.db.sql=mysql
server.db.path=com.aiesst.model.entity
#--------------------postgres数据库配置----------------------#
server.pg.host=localhost
server.pg.port=5432
server.pg.username=postgres
server.pg.password=112211
server.pg.database=postgres
server.pg.driver.class=org.postgresql.Driver
server.pg.jdbc.url=jdbc:postgresql://${server.pg.host}:${server.pg.port}/${server.pg.database}
#server.pg.jdbc.url=jdbc:postgresql://localhost:5432/postgres
#--------------------mysql数据库配置----------------------#
server.mysql.host=localhost
server.mysql.port=3306
server.mysql.username=root
server.mysql.encoding=utf-8
server.mysql.password=112211
server.mysql.driver.class=com.mysql.jdbc.Driver
server.mysql.jdbc.url=jdbc:mysql://${server.mysql.host}:${server.mysql.port}/dev?useUnicode=true&characterEncoding=UTF-8&useSSL=false
```

