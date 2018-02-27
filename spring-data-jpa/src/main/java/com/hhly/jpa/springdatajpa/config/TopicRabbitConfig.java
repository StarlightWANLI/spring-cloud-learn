package com.hhly.jpa.springdatajpa.config;

import com.hhly.jpa.springdatajpa.mq.ConfirmCallBackListener;
import com.hhly.jpa.springdatajpa.mq.ReturnCallBackListener;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 总结下就是:

 如果消息没有到exchange,则confirm回调,ack=false

 如果消息到达exchange,则confirm回调,ack=true

 exchange到queue成功,则不回调return

 exchange到queue失败,则回调return(需设置mandatory=true,否则不回回调,消息就丢了)


 //声明消费的queue
 //注意，RabbitMQ不允许对一个已经存在的队列用不同的参数重新声明，对于试图这么做的程序，会报错，所以，改动之前代码之前，要在控制台中把原来的队列删除
 //IO异常:java.io.IOException
 boolean durable = true;//是否持久化消息,无论是提供者还是消费者,都可以设置,

 */
@Configuration
public class TopicRabbitConfig {

    final static String message = "topic.message";
    final static String messages = "topic.messages";


    @Bean
    public Queue queueMessage() {
        //持久化队列   有什么好处，怎么使用
        //new Queue(TopicRabbitConfig.message,ture)
        return new Queue(TopicRabbitConfig.message,true);
    }

    @Bean
    public Queue queueMessages() {
        return new Queue(TopicRabbitConfig.messages,true);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("topicExchange");
    }


    /**
     * 绑定队列，并接收指定topic的数据
     *
     * 声明了多个队列时，绑定的时候要指定对应的方法，不能直接通过参数绑定；交换器也是一样的
     */
/*      @Bean
  Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        //精准匹配发送到topic.message
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }*/
/*    @Bean
    Binding bindingExchangeMessage() {
        //精准匹配发送到topic.message
        return BindingBuilder.bind(queueMessage()).to(exchange()).with("topic.message");
    }*/

    /**
     * 优先完全匹配，之后再模糊匹配
     * 有完全匹配的首先完全匹配，比如  routingkey为    topic.message  ，因为存在topic.message的队列，所以只和topic.message的队列匹配，不匹配topic.#
     *
     *而交换器topicExchange下，routingkey为    topic.messages或 topic.1，都只能和topic.#匹配，而topic.#绑定了2个队列
     */
/*    @Bean
    Binding bindingExchangeMessage1(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.#");
    }*/


 /*
   rabbitmq中，有些配置在项目启动生效后，再注释代码重启项目，配置可能仍然有效

    需要通过  rabbitmq的管理界面，手动去重新配置
    http://localhost:15672/#/exchanges/pay/topicExchange
*/
  @Bean
   Binding bindingExchangeMessages() {
        //*表示一个词,#表示零个或多个词          ,通过模糊匹配的消息，都发送到topic.messages队列
        return BindingBuilder.bind(queueMessages()).to(exchange()).with("topic.#");
    }



    //设置监听

    /**
     *  消费端主要有两个重点，一个是设置应答模式是手动模式，一个是什么时候调用basicAck()方法，什么时候调用basicNack()方法
     */
    @Bean
    public SimpleMessageListenerContainer messageContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        //只绑定topic.message的消息队列，其它消息队列里面的消息不做确认
        container.setQueues(queueMessage());
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(10);//最大
        container.setConcurrentConsumers(1);//最少
        //设置确认模式手工确认
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        //设置监听器，执行具体的业务
        container.setMessageListener(new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                try{
                    System.out.println("consumer--:"+message.getMessageProperties()+":"+new String(message.getBody()));
                    channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                }catch(Exception e){
                    e.printStackTrace();//TODO 业务处理
                    channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
                }
                //如果处理失败
                 //channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
                /*接收到消息，处理业务成功则调用channel.basicAck()方法，告诉mq服务器消费端处理成功，mq服务器就会删除该消息，如果处理失败可以调用basicNack()方法，调用该方法之后，服务器会自动的重新发送该消息，让消费端重新处理，直到消费端返回basicAsk
                消费端主要有两个重点，一个是设置应答模式是手动模式，一个是什么时候调用basicAck()方法，什么时候调用basicNack()方法*/
            }
        });
        return container;
    }


    @Bean
    public SimpleMessageListenerContainer messageContainer2(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueues(queueMessages());
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(1);
        container.setConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
        container.setMessageListener(new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                byte[] body = message.getBody();
                System.out.println("消息确认receive topic.messages: " + new String(body));
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); //确认消息成功消费
            }
        });
        return container;
    }
}

