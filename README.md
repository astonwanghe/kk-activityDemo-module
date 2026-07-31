# kk-activityDemo-module

一个基于 Spring Cloud 的活动示例服务。服务通过 Feign 调用 `kk-user-server` 获取用户信息，并返回包含用户昵称、排名和随机积分的排行榜数据。

## 模块说明

| 模块 | 说明 |
| --- | --- |
| `kk-activityDemo-api` | 对外接口定义及 `UserRankDTO` 数据模型，可供其他服务依赖。 |
| `kk-activityDemo-server` | 服务实现：注册到 Eureka，并通过 Feign 调用用户服务。 |

## 技术栈

- Java 8
- Maven
- Spring Boot 1.5.14.RELEASE
- Spring Cloud Edgware.SR3
- Eureka Client、OpenFeign、Hystrix、Ribbon
- Log4j2

## 前置条件

启动前请准备：

1. JDK 8 和 Maven 3.x。
2. Eureka 注册中心运行在 `http://localhost:8761/eureka`。
3. 名为 `kk-user-server` 的用户服务已注册到 Eureka。
4. 本地 Maven 仓库中存在依赖 `com.melot.kk.springcloud:kk-user-api:1.0.0-SNAPSHOT`。该依赖不包含在本仓库中，需要先从对应项目安装：

   ```bash
   mvn install
   ```

## 构建与启动

在项目根目录执行：

```bash
# 编译 API 和服务端模块
mvn -pl kk-activityDemo-server -am package

# 启动服务
mvn -pl kk-activityDemo-server spring-boot:run
```

服务默认监听 `8763` 端口，应用名为 `kk-activityDemo-server`。配置位于 [application.properties](kk-activityDemo-server/src/main/resources/application.properties)。

## 接口说明

### 查询用户排行榜信息

```http
GET /demo/getUserRank?userId={userId}
```

请求示例：

```bash
curl 'http://localhost:8763/demo/getUserRank?userId=10001'
```

成功时响应示例：

```json
{
  "rank": 1,
  "userId": 10001,
  "nickname": "demo-user",
  "score": 42
}
```

`score` 为当前示例随机生成的 0–99 整数；当用户不存在，或 `kk-user-server` 不可用而触发 Hystrix 降级时，接口返回空响应。

## 配置要点

- Eureka 地址：`eureka.client.service-url.defaultZone`
- 用户服务请求连接超时：1 秒
- 用户服务请求读取超时：8 秒
- Hystrix 超时：10 秒

可按部署环境修改 `kk-activityDemo-server/src/main/resources/application.properties`。
