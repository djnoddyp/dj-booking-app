package pnodder.config;

//import org.apache.activemq.command.ActiveMQTopic;
//import org.apache.activemq.spring.ActiveMQConnectionFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.jms.listener.DefaultMessageListenerContainer;
//import pnodder.messaging.OrderListener;

//@Configuration
//public class MessagingConfig {
//
//    @Bean
//    public ActiveMQConnectionFactory activeMQConnectionFactory() {
//        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
//        activeMQConnectionFactory.setBrokerURL("tcp://localhost:61616");
//        return activeMQConnectionFactory;
//    }
//
////    @Bean
////    public ActiveMQTopic activeMQTopic() {
////        return new ActiveMQTopic("order.topic");
////    }
//
//    @Bean
//    public DefaultMessageListenerContainer jmsContainer() {
//        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
//        container.setConnectionFactory(activeMQConnectionFactory());
//        container.setDestinationName("order.topic");
//        container.setMessageListener(orderListener());
//        return container;
//    }
//
//    @Bean
//    public JmsTemplate jmsTemplate() {
//        JmsTemplate jmsTemplate = new JmsTemplate();
//        jmsTemplate.setConnectionFactory(activeMQConnectionFactory());
//        jmsTemplate.setDefaultDestinationName("order.topic");
//        return jmsTemplate;
//    }
//
//    @Bean
//    public OrderListener orderListener() {
//        return new OrderListener();
//    }

//}
