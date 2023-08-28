package dsg.unibamberg.assignment1.service.implementation;

import dsg.unibamberg.assignment1.model.Order;
import dsg.unibamberg.assignment1.model.OrderItem;
import dsg.unibamberg.assignment1.repository.OrderRepository;
import dsg.unibamberg.assignment1.service.IOrderItemService;
import dsg.unibamberg.assignment1.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;

    private final IOrderItemService orderItemService;

    @Autowired
    public OrderService(OrderRepository orderRepository, IOrderItemService orderItemService) {
        this.orderRepository = orderRepository;
        this.orderItemService = orderItemService;
    }

    @Override
    public Order findOrderById(Long orderId) {
        Order order = this.orderRepository.findById(orderId).orElse(null);
        return order;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = this.orderRepository.findAll();
        return orders;
    }

    @Override
    public void createOrder(Order order) {
        List<OrderItem> orderItems = new ArrayList<>(order.getOrderItems());
        Order orderFromDB = this.orderRepository.save(order);
//            this.orderItemService.save(orderItems);
    }
}
