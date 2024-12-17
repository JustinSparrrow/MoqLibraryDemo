# 图书馆后端技术路线

---

## 1. 预备知识
### 1.1 Web基础知识
在Web开发中，需要了解URL、TCP、UDP、HTTP等内容。
#### 1.1.1 URL
URL是用于标识互联网上资源的位置和访问方式的字符串。组成部分：协议+主机+路径+查询参数。
#### 1.1.2 UDP
UDP是一种无连接的传输层协议。他在发送数据之前不需要建立连接，直接把数据发送出去。UDP数据报的头部比较简单，只包含源端口、目的端口、长度和校验和等少量信息。
#### 1.1.3 TCP
TCP是一种面向连接的、可靠的传输层协议。在数据传输之前，通信双方需要通过三次握手建立连接，数据传输完成后，还需要通过四次挥手拆除连接。TCP通过序列号、确认号、重传机制等来保证数据的可靠传输。
#### 1.1.4 HTTP
HTTP是一种无状态协议，它定义了客户端与服务器之间的通信规则。HTTP请求包括：
- 请求行：包含方法（如 GET、POST）、URL 路径和协议版本。
- 请求头：用于传递元数据，比如 Content-Type（声明数据格式）或 Authorization（身份认证）。
- 请求体：包含发送给服务器的数据，通常出现在 POST 或 PUT 请求中。

HTTP响应包括：
- 状态码：如 200 OK（成功）、404 Not Found（资源不存在）。 
- 响应头：如 Content-Length（内容长度）、Set-Cookie（设置 Cookie）。 
- 响应体：服务器返回的数据内容，比如 HTML 页面或 JSON 数据。

#### 1.1.5 Cookie和Session
Cookie和Session是Web应用中常用的状态保持机制，Cookie存储在客户端，通常用于保存用户的登录状态。Session存储在服务器端，通过Session ID来标识用户会话，安全性更高。
### 1.2 Servlet

### 1.3 Tomcat并发原理
### 1.4 Maven
### 1.5 Docker

## 2. Spring
### 2.1 控制反转
### 2.2 SpringBean
### 2.3 Spring容器
### 2.4 SpringBoot

## 3. SpringMVC
### 3.1 RestfulAPI
### 3.2 SpringMVC
### 3.3 跨域性访问问题

## 4. MyBatis
### 4.1 对象关系映射框架
### 4.2 对象关系模式映射
### 4.3 MyBatis

## 5. MySQL原理
### 5.1 InnoDB原理
### 5.2 InnoDB索引
### 5.3 InnoDB事务
### 5.4 MySQL的MVCC
### 5.5 MVCC实现原理

## 6. AOP
### 6.1 AOP介绍
### 6.2 实例
#### 6.2.1 合法性检查
#### 6.2.2 pageSize和异常处理
#### 6.2.3 JWT

## 7. Spring测试
### 7.1 白盒测试和黑盒测试
### 7.2 Junit单元测试
### 7.3 dao层对象的测试
### 7.4 service和controller层对象测试
### 7.5 jacoco和如何调试

## 8. 缓存
### 8.1 MyBatis缓存
### 8.2 Redis缓存
### 8.3 Redis数据结构
### 8.4 Bloom过滤器和Spring Cache
### 8.5 Redis内存管理

## 9. SpringData和JPA
### 9.1 Spring Data
### 9.2 Spring Data JPA
### 9.3 JPQL

## 10. Mongo和Spring Data Mongo
### 10.1 Mongo介绍
### 10.2 Mongo使用
### 10.3 Spring Data Mongo

## 11. 消息服务器和RocketMQ
### 11.1 消息服务的概念
### 11.2 RocketMQ的结构
### 11.3 普通消息和延时消息
### 11.4 事务消息
### 11.5 消息服务器的作用

## 12. 微服务架构
### 12.1 微服务定义
### 12.2 微服务原则
### 12.3 Spring Cloud
### 12.4 OpenFeign
### 12.5 Spring Cloud Gateway

