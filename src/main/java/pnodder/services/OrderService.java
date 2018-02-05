package pnodder.services;

import org.springframework.stereotype.Service;
import pnodder.messaging.OrderSender;
import pnodder.model.Order;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
public class OrderService {

    private OrderSender orderSender;

    public OrderService(OrderSender orderSender) {
        this.orderSender = orderSender;
    }

    public void submitOrder(Order order) {
        orderSender.send(order);
    }
}
