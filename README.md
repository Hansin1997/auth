# Auth

[简介](#简介) | [展示](#展示) | [部署](#部署) | [构建](#构建) | [鸣谢](鸣谢)

## 简介

**Auth** 是一个基于 **Spring Boot** 的 **OAuth2.0** 的授权与管理服务。 提供 OAuth2.0 授权服务，以及包括用户、应用、角色与权限的管理。 提供 Restful 接口以及 OpenAPI 文档。
提供前端页面。 支持微服务部署。

> [在线展示](#展示)

### 后端

* 通过重写 **Spring Security OAuth2** 中的 Endpoint 实现前后端分离架构，使用 **Redis** 存储 Token、授权码、验证码等。
* 基于 **MySQL** & **MyBatis** 实现包括用户管理、应用管理、权限与角色管理等业务。
* 文件储存通过第三方服务实现，登录注册等接口通过谷歌 reCAPTCHA 人机识别进行验证。
* 使用 **Spring Doc** & **Swagger-UI** 生成 **OpenAPI** 文档和 API 调试页面。

### 前端

* 基于 **Vue** & **Quasar** 框架。
* 使用 **OpenAPI Generator** 生成 Typescript Axios SDK。
* 国际化（中英文） & 自适应。

### 关键词

* OAuth2.0 授权中心
* 无状态（Stateless）
* 角色权限（RBAC）

## 展示

### 前端展示

包含登录注册、应用授权、个人信息管理、应用管理以及用户管理和系统设置。
[前往查看](https://accounts.dustlight.cn)

您可以使用下列的测试账号登录，它们拥有测试权限以及开发者权限。

| 用户名 | 密码 |
| --- | --- |
| test_user | 123456 |
| aaaaaa | 123456 |
| bbbbbb | 123456 |

> 1. 出于功能展示的目的，测试账号拥有查看所有用户信息的权限。如果您打算进行注册账号，您的电子邮箱地址可能会被其他人看到。
> 2. 若上述测试账号均不可用，请 [创建 Issue](https://github.com/dustlight-cn/auth/issues/new) 联系我们。

### 接口浏览

| Open API | URL |
| --- | --- |
| JSON | [https://api.dustlight.cn/v1/api-doc](https://api.dustlight.cn/v1/api-doc) | 
| YAML | [https://api.dustlight.cn/v1/api-doc.yaml](https://api.dustlight.cn/v1/api-doc.yaml) | 

> 后端接口浏览调试：
> [前往查看（Swagger UI）](https://api.dustlight.cn/v1/swagger-ui)

## 部署

### Docker 镜像

```dustlightcn/auth-service```
![Docker Image Size (latest semver)](https://img.shields.io/docker/image-size/dustlightcn/auth-service?logo=docker)
![Docker Image Version (latest semver)](https://img.shields.io/docker/v/dustlightcn/auth-service?label=version)

### Kubernetes配置

待完善

### ...

## 构建

如果您需要自己构建项目，可以参考以下步骤进行构建。

### 构建后端服务

执行 ```mvn package```

### 构建前端 UI

进入目录 ```cd auth-ui```

执行 ```quasar build```

### 构建 Docker 镜像

复制构建完成的二进制文件到根目录，将其重命名为 ```auth-service.jar```

执行 ```docker build -t auth-service:0.0.1 .```

## 鸣谢

<a href="https://www.jetbrains.com/?from=Auth">
<svg id="Layer_1" data-name="Layer 1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 120.21407 130.10375">
  <title>logo-grey</title>
  <g>
    <path d="M120.2,68.6a4.99684,4.99684,0,0,0-1.4-3.5L55.191,2.68736a7.86372,7.86372,0,0,0-3.36731-2.261c-.03467-.012-.06812-.02612-.103-.03766-.19336-.06323-.3913-.11511-.591-.16327-.06689-.0163-.13177-.0368-.1994-.05133-.18744-.04-.37909-.06738-.57117-.09363-.0788-.0108-.15527-.0274-.23492-.03589A7.83914,7.83914,0,0,0,49.3,0a7.73961,7.73961,0,0,0-1.21088.10413c-.0235.00391-.04694.00671-.07037.0108a7.62573,7.62573,0,0,0-3.092,1.24969c-.07343.04773-.155.08575-.22668.13538L4.9,28.3c-.08221.08221-.15106.10632-.17773.16437L4.67218,28.5H4.6A11.11647,11.11647,0,0,0,.15875,39.45683l.00854.04449c.05176.28589.11011.56958.18372.84973.03052.119.06964.235.104.35284.054.181.10278.3634.16571.5412A11.15109,11.15109,0,0,0,5.3,47.1a8.82025,8.82025,0,0,0,2,.9c.4.2,45.4,18.8,45.4,18.8a4.291,4.291,0,0,0,4.4-7.3c-.06525,0-16.839-13.21332-28.69928-22.52606l21.105-19.03113,57.91815,49.58282L28.6,110.7a9.82332,9.82332,0,0,0-4.7,4.1,10.0662,10.0662,0,0,0,3.6,13.9,10.28689,10.28689,0,0,0,10.7-.2c.2-.2.5-.3.7-.5L116.9,73.2a18.32,18.32,0,0,0,1.58612-1.2663A4.74573,4.74573,0,0,0,120.2,68.6Z" transform="translate(0.01406 0.00002)" fill="#cdcdcd"/>
    <g id="_Group_" data-name="&lt;Group&gt;">
      <rect id="_Path_" data-name="&lt;Path&gt;" x="34.61406" y="37.40002" width="51" height="51"/>
      <rect id="_Path_2" data-name="&lt;Path&gt;" x="39.01406" y="78.80002" width="19.1" height="3.2" fill="#fff"/>
      <g id="_Group_2" data-name="&lt;Group&gt;">
        <path id="_Path_3" data-name="&lt;Path&gt;" d="M38.8,50.8l1.5-1.4a1.70271,1.70271,0,0,0,1.3.8q.9,0,.9-1.2V43.7h2.3V49a2.79543,2.79543,0,0,1-3.1,3.1A3.026,3.026,0,0,1,38.8,50.8Z" transform="translate(0.01406 0.00002)" fill="#fff"/>
        <path id="_Path_4" data-name="&lt;Path&gt;" d="M45.3,43.8H52v1.9H47.6V47h4v1.8h-4v1.3h4.5v2H45.4Z" transform="translate(0.01406 0.00002)" fill="#fff"/>
        <path id="_Path_5" data-name="&lt;Path&gt;" d="M55,45.8H52.5v-2h7.3v2H57.3v6.3H55Z" transform="translate(0.01406 0.00002)" fill="#fff"/>
        <path id="_Compound_Path_" data-name="&lt;Compound Path&gt;" d="M39,54h4.3a3.7023,3.7023,0,0,1,2.3.7,1.97822,1.97822,0,0,1,.5,1.4h0A1.95538,1.95538,0,0,1,44.8,58a1.94762,1.94762,0,0,1,1.6,2h0c0,1.4-1.2,2.3-3.1,2.3H39Zm4.8,2.6c0-.5-.4-.7-1-.7H41.3v1.5h1.4c.7-.1,1.1-.3,1.1-.8ZM43,59H41.2v1.5H43c.7,0,1.1-.3,1.1-.8h0C44.1,59.2,43.7,59,43,59Z" transform="translate(0.01406 0.00002)" fill="#fff"/>
        <path id="_Compound_Path_2" data-name="&lt;Compound Path&gt;" d="M46.8,54h3.9a3.51463,3.51463,0,0,1,2.7.9,2.48948,2.48948,0,0,1,.7,1.9h0a2.76053,2.76053,0,0,1-1.7,2.6l2,2.9H51.8l-1.7-2.5h-1v2.5H46.8Zm3.8,4c.8,0,1.2-.4,1.2-1h0c0-.7-.5-1-1.2-1H49.1v2Z" transform="translate(0.01406 0.00002)" fill="#fff"/>
        <path id="_Compound_Path_3" data-name="&lt;Compound Path&gt;" d="M56.8,54H59l3.5,8.4H60l-.6-1.5H56.2l-.6,1.5H53.2Zm2,5-.9-2.3L57,59Z" transform="translate(0.01406 0.00002)" fill="#fff"/>
        <path id="_Path_6" data-name="&lt;Path&gt;" d="M62.8,54h2.3v8.3H62.8Z" transform="translate(0.01406 0.00002)" fill="#fff"/>
        <path id="_Path_7" data-name="&lt;Path&gt;" d="M65.7,54h2.1l3.4,4.4V54h2.3v8.3h-2L68,57.8v4.6H65.7Z" transform="translate(0.01406 0.00002)" fill="#fff"/>
        <path id="_Path_8" data-name="&lt;Path&gt;" d="M73.7,61.1,75,59.6a3.94219,3.94219,0,0,0,2.7,1c.6,0,1-.2,1-.6h0c0-.4-.3-.5-1.4-.8-1.8-.4-3.1-.9-3.1-2.6h0c0-1.5,1.2-2.7,3.2-2.7A5.33072,5.33072,0,0,1,80.8,55l-1.2,1.6a4.55346,4.55346,0,0,0-2.3-.8c-.6,0-.8.2-.8.5h0c0,.4.3.5,1.4.8,1.9.4,3.1,1,3.1,2.6h0c0,1.7-1.3,2.7-3.4,2.7A5.29336,5.29336,0,0,1,73.7,61.1Z" transform="translate(0.01406 0.00002)" fill="#fff"/>
      </g>
    </g>
  </g>
</svg>
</a>
