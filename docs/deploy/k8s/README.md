# Kubernetes 部署
## 一键部署
使用 ```kubectl``` 一键部署服务，集群中需要至少两个持久卷（```PersistentVolume```）用于 MySQL 数据库 与 Redis 的持久化。

执行：
```bash
kubectl apply -f https://raw.githubusercontent.com/dustlight-cn/auth/master/docs/deploy/k8s/deploy.yaml
```

## 文档待完善
