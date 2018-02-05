package pnodder.messaging;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import pnodder.model.Order;

import javax.jms.*;
import java.util.HashMap;
import java.util.Map;

@Component
public class OrderSender {

    private static final String ORDER_TOPIC ="order.topic";

    private JmsTemplate jmsTemplate;

    public OrderSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void send(Order order)  {
        Map bike = new HashMap();
        bike.put("id", order.getBike().getId());
        bike.put("make", order.getBike().getMake());
        bike.put("model", order.getBike().getModel());
        bike.put("price", order.getBike().getPrice());

        Map orderMap = new HashMap();
        orderMap.put("id", "unassigned");
        orderMap.put("email", order.getEmail());
        orderMap.put("bike", bike);

        jmsTemplate.convertAndSend(ORDER_TOPIC, orderMap);
    }

}
