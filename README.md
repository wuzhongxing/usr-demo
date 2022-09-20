## 上线准备

- 准备配置
- 准备mysql数据库环境，并新建表

## 模块介绍
- usr-demo-common  常用依赖jar包，发不成公用jar包，供各服务使用
  
- usr-demo-api  服务接口模块，发不成公用jar包，供各服务使用

- usr-demo-dao  服务dao层，封装数据库操作

- usr-demo-core 服务业务层，处理具体业务逻辑

- usr-demo-provider  服务provider层，可较容易的封装成其他rpc服务，供consumer调用。

- usr-demo-web   web层

##生成api
- 生成doc api： mvn -DskipTests=true com.github.apiggs:apiggs-maven-plugin:1.6:apiggs
