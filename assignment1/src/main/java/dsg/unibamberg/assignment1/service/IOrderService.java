package dsg.unibamberg.assignment1.service;

import dsg.unibamberg.assignment1.model.Order;

import java.util.List;

public interface IOrderService {
    Order findOrderById(Long orderId);

    List<Order> findAll();

    void createOrder(Order order);
}
