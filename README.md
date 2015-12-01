# 陈涛的展示项目

网址：https://github.com/chenbuu/demo

手机号码： 15268639007

Email：1393899065@qq.com

项目背景介绍：浙江金华公共自行车租聘公司和浙江师范大学联合推出校园版骑车宝手机APP。本系统上上述项目的主要后台框架。

项目技术介绍：本项目采用了MongoDB、Redis、Querydsl、Spring Boot、Spring Rest、Maven、Lombok、AngularJS、Bootstrap、ionic等

# 架构技术明细

分布式数据库： MongoDB，采用集群分布式NoSQL的存储方案。MongoDB是非关系数据库当中功能最丰富，最像关系数据库的。NoSQL(NoSQL = Not Only SQL )，意即"不仅仅是SQL"。分布式系统（distributed system）由多台计算机和通信的软件组件通过计算机网络连接（本地网络或广域网）组成。

分布式缓存：Redis，采用集群分布式缓存系统。Redis不仅仅支持简单的key-value类型的数据，同时还提供list，set，zset，hash等数据结构的存储。性能极高 – Redis能读的速度是110000次/s,写的速度是81000次/s。丰富的数据类型 – Redis支持二进制案例的 Strings, Lists, Hashes, Sets 及 Ordered Sets 数据类型操作。

分布式大文件存储：MongoDB GridFS，采用多数据源的方案（本人封装的多MongoDB链接方案，因为Spring Boot暂时不支持多MongoDB）。MongoDB GridFS 会将大文件对象分割成多个小的chunk(文件片段),一般为256k/个,每个chunk将作为MongoDB的一个文档(document)被存储在chunks集合中。

分布式日志：Cassandra，开发中。。。

分布式搜索引擎：Solr，开发中。。。

分布式消息中间件：RabbitMQ，开发中。。。

分布式虚拟化：Docker，开发中。。。

分布式计算：Hadoop，开发中。。。

# 前端技术明细

待开发。。。