注册中心：
这里采用高可用配置 :

注册到注册中心的服务ID
在springcloud中服务的 Instance ID 默认值是:
${spring.cloud.client.hostname}:${spring.application.name}:${spring.application.instance_id:${server.port}}


#表示是否将自己注册到Eureka Server，默认为true。由于当前这个应用就是Eureka Server，故而设为false
eureka.client.register-with-eureka=false
#表示是否从Eureka Server获取注册信息，默认为true。因为这是一个单点的Eureka Server，不需要同步其他的Eureka Server节点的数据，故而设为false。
eureka.client.fetch-registry=false
##设置与Eureka Server交互的地址，查询服务和注册服务都需要依赖这个地址。默认是http://localhost:8761/eureka ；多个地址可使用 , 分隔。
eureka.client.service-url.defaultZone=http://${eureka.instance.hostname}:${server.port}/eureka/

#Eureka Server端：配置关闭自我保护，并按需配置Eureka Server清理无效节点的时间间隔。
## 设为false，关闭自我保护
eureka.server.enable-self-preservation=false
## 清理间隔（单位毫秒，默认是60*1000）
eureka.server.eviction-interval-timer-in-ms=60000



成功的标志：
启动register1，register2后，
访问http://register1:9010/，进入注册中心管理页面，看到DS Replicas是  register2,
访问http://register2:9011/，进入注册中心管理页面，看到DS Replicas是  register1

说明，register1和register2互为对方的备份

在Instances currently registered with Eureka中都能看到register实例，说明register本身
也作为一个服务实例被注册到注册中心上










