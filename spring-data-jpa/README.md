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

4、首次启动时通过Flyway自动初始化数据库（完成）
    主要的对数据库脚本的版本进行控制
    http://blog.csdn.net/kingice1014/article/details/70263083
    
5、使用mock模拟请求Controller对功能进行单元测试（完成）

6、使用hibernate-validator进行数据校验

7、跨域问题的解决（完成）

8、异常的全局统一处理（完成）

9、使用注解、切面实现日志记录（完成）

10、rabbitmq的使用
http://www.cnblogs.com/ityouknow/p/6120544.html

rabbitmq的4中交换器类型：
类型有4种，direct，fanout，topic，headers。

rabbitmq的ack应答机制：消息消费确认回调
ack应答有两种方式：1、自动应答，2、手动应答
QueueingConsumer consumer = new QueueingConsumer(channel);  
boolean autoAck = false;//默认的是true，自动确认  
channel.basicConsume("hello", autoAck, consumer);  
while (true) {  
  QueueingConsumer.Delivery delivery = consumer.nextDelivery();  //此时，consumer可能已经从rabbitmq获得和多个消息  
  channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);  
  //delivery.getEnvelope().getDeliveryTag()消息的标识，false只确认当前一个消息收到，true确认所有consumer获得的消息  
}  

rabbitmq的持久化机制：
核心代码：
        channel.queueDeclare(queue_name, durable, false, false, null); //声明消息队列，且为可持久化的
         String message="Hello world"+Math.random();
         //将队列设置为持久化之后，还需要将消息也设为可持久化的，MessageProperties.PERSISTENT_TEXT_PLAIN
         channel.basicPublish("", queue_name, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());

消息什么时候需要持久化？
根据 官方博文 的介绍，RabbitMQ在两种情况下会将消息写入磁盘：
1、消息本身在publish的时候就要求消息写入磁盘；
2、内存紧张，需要将部分内存中的消息转移到磁盘；

公平的消息分发
     目前的消息队列，分发消息时没有考虑consumer的具体情况，有可能造成有的consumer负载过重，有的consumer负载太轻；
     应该考虑consumer没有确认消息的数量，如果unacked的消息过多，则应该少往此consumer发送消息；


int prefetchCount = ;//maximum number of messages that the server will deliver, 0 if unlimited  
channel.basicQos(prefetchCount);//  

11、集成quartz定时任务

12、redis缓存、分布式锁的使用

13、spring boot中拦截器interceptor、order、advice、fiter、commandLineRunner（完成）
@Order标记定义了组件的加载顺序。
@Order标记从spring 2.0出现，但是在spring 4.0之前，@Order标记只支持AspectJ的切面排序。spring 4.0对@Order做了增强，
它开始支持对装载在诸如Lists和Arrays容器中的自动包装（auto-wired）组件的排序。
Spring 4.2 利用@Order控制配置类的加载顺序



14.spring session的使用




更新日期：
12.20主要使用集成spring data jpa的各种使用示例


约定使用domain包来存放微服务的领域实体对象（即操作和核心业务相关实体，能进行数据的curd操作的）
使用model包来保存领域无关的实体对象