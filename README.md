# Auth


[![Docker Build Status](https://img.shields.io/github/workflow/status/dustlight-cn/auth/Docker%20Image%20CI)](https://github.com/dustlight-cn/auth/actions/)
[![GitHub release (latest by date including pre-releases)](https://img.shields.io/github/v/release/dustlight-cn/auth?include_prereleases)](https://github.com/dustlight-cn/auth/releases)
[![License](https://img.shields.io/github/license/dustlight-cn/auth)](LICENSE)


[![Docker Image Size (latest semver)](https://img.shields.io/docker/image-size/dustlightcn/auth-service?logo=docker)](https://hub.docker.com/repository/docker/dustlightcn/auth-service)
[![Docker Image Version (latest semver)](https://img.shields.io/docker/v/dustlightcn/auth-service?label=image%20version&logo=docker)](https://hub.docker.com/repository/docker/dustlightcn/auth-service)

[简介](#简介) | [文档](docs) | [部署](docs/deploy) | [构建](docs/Build.md) | [鸣谢](#鸣谢)

## 简介

**Auth** 是一个前后端分离的 OAuth2.0 授权中心与用户中心，适用于 **微服务鉴权**、**单点登录**、**企业开放平台** 等场景。

#### [👀 在线展示](#在线展示)

#### [🕵️‍♀️ 图片展示](#图片展示)

### 功能包括
* 用户管理
* 应用管理
* 角色与权限管理
* OAuth2 授权模式与授权作用域管理 （GrantType & Scope）
* 应用授权与鉴权

### 特点
* 无状态的 Restful 服务
* 简洁、自适应、双语言的前端页面
* 支持快速微服务部署

### 支持的授权模式

* 授权码模式 *authorization_code* ✔
* 客户端凭据模式 *client_credentials* ✔
* 隐式授权模式 *implicit* ✔
* 令牌刷新 *refresh_token* ✔
* 密码模式 *password* （默认不启用，如需启用可以创建授权模式。）

## 快速开始
您可以参考 [部署文档](docs/deploy) 开始使用 **Auth**。

构建、配置请参考 [文档](docs)。

### Kubernetes 部署后端服务
> 使用 ```kubectl``` 一键部署服务，集群中需要至少两个持久卷（```PersistentVolume```）用于 MySQL 数据库 与 Redis 的持久化。

```bash
kubectl apply -f https://raw.githubusercontent.com/dustlight-cn/auth/master/docs/deploy/k8s/deploy.yaml
```

其他部署方式请参考：[部署文档](docs/deploy)

### 前端部署
请参考：[部署文档](docs/deploy)

## 展示
### 在线展示
[前往查看](https://accounts.dustlight.cn)

若您不想注册账号或者像想体验更高权限的功能，可以使用 [测试账号](#测试账号) 。

#### 测试账号
> 1. 出于功能展示的目的，测试账号拥有查看所有用户信息的权限。如果您打算进行注册账号，您的电子邮箱地址可能会被其他人看到。
> 2. 若以下测试账号均不可用，请 [创建 Issue](https://github.com/dustlight-cn/auth/issues/new) 联系我。

您可以使用下列的测试账号登录，它们拥有测试权限以及开发者权限。

| 用户名 | 密码 |
| --- | --- |
| test_user | 123456 |
| aaaaaa | 123456 |
| bbbbbb | 123456 |

#### OAuth2 授权页面与端点

| 功能（前端） | URL | 示例 |
| --- | --- | --- |
| 授权 | [/authorize](https://accounts.dustlight.cn/authorize) | [隐式授权示例 *(implicit)*](https://live.dustlight.cn/login?redirect_uri=%2Fhome) |

| 功能（后端） | URL | 方法 |
| --- | --- | --- |
| 颁发 OAuth 令牌 | [/v1/oauth/token](https://api.dustlight.cn/v1/oauth/token) | ```POST``` |
| 销毁令牌 | [/v1/token](https://api.dustlight.cn/v1/token) | ```DELETE``` |
| 检查令牌有效性 | [/v1/token/validity](https://api.dustlight.cn/v1/token/validity) | ```GET``` / ```POST``` |

#### 接口浏览

| Open API | URL |
| --- | --- |
| JSON | [https://api.dustlight.cn/v1/api-doc](https://api.dustlight.cn/v1/api-doc) | 
| YAML | [https://api.dustlight.cn/v1/api-doc.yaml](https://api.dustlight.cn/v1/api-doc.yaml) | 
| Swagger UI | [https://api.dustlight.cn/v1/swagger-ui](https://api.dustlight.cn/v1/swagger-ui) |

### 图片展示
#### 授权
![authorize](https://raw.githubusercontent.com/dustlight-cn/auth/master/docs/img/authorize.jpeg)

#### 用户详情
![user details](https://raw.githubusercontent.com/dustlight-cn/auth/master/docs/img/user_details.jpeg)

#### 应用详情
![client details](https://raw.githubusercontent.com/dustlight-cn/auth/master/docs/img/client_details.jpeg)

#### 系统设置
![settings](https://raw.githubusercontent.com/dustlight-cn/auth/master/docs/img/settings.jpeg)

## 实现原理
### 后端

* 通过重写 **Spring Security OAuth2** 中的 Endpoint 实现前后端分离架构，使用 **Redis** 存储 Token、授权码、验证码等。
* 基于 **MySQL** & **MyBatis** 实现包括用户管理、应用管理、权限与角色管理等业务。
* 文件储存通过第三方服务实现（同时兼容本地储存），登录注册等接口通过谷歌 reCAPTCHA 人机识别进行验证。
* 使用 **Spring Doc** & **Swagger-UI** 生成 **OpenAPI** 文档和 API 调试页面。


### 前端

* 基于 **Vue** & **Quasar** 框架。
* 使用 **OpenAPI Generator** 生成 Typescript Axios SDK。

## 鸣谢

[![JetBrains](https://raw.githubusercontent.com/dustlight-cn/auth/master/docs/img/jetbrains.svg)](https://www.jetbrains.com/?from=Auth)
