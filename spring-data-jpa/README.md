**自导项目的演进**

目标：
针对spring cloud体系结构中的各种技术框架的演练，
第一期，预计集成spring swagger2，spring data jpa，spring data redis，rabbitmq，spring session等的使用

核心的知识体系如下：
1、spring swagger2的配置  （完成）

2、spring data jpa 的使用（针对jpa，会用就好，不深究了，动态条件查询和分页支持的太弱）
   Querydsl   查询插件的使用  https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#core.extensions.querydsl

   和mybatis整合一起使用    （完成）

3、jasypt-spring-boot   对明文密码配置项进行加密（完成）
     推荐使用1.16版本，在使用1.17版本的时候，读取不到application.properties中的jasypt的配置属性，导致项目启动的时候就报错

4、首次启动时通过Flyway自动初始化数据库
    主要的对数据库脚本的版本进行控制
    http://blog.csdn.net/kingice1014/article/details/70263083
    

5、使用mock模拟请求Controller对功能进行单元测试

6、使用hibernate-validator进行数据校验

7、跨域问题的解决

8、异常的全局统一处理

9、使用注解、切面实现日志记录

10、rabbitmq的使用
http://www.cnblogs.com/ityouknow/p/6120544.html

11、集成quartz定时任务

12、redis缓存、分布式锁的使用

13、spring boot中拦截器interceptor、order、advice、fiter、commandLineRunner


更新日期：
12.20主要使用集成spring data jpa的各种使用示例


约定使用domain包来存放微服务的领域实体对象（即操作和核心业务相关实体，能进行数据的curd操作的）
使用model包来保存领域无关的实体对象