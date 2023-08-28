package dsg.unibamberg.assignment1.service.implementation;

import dsg.unibamberg.assignment1.model.Beverage;
import dsg.unibamberg.assignment1.model.OrderItem;
import dsg.unibamberg.assignment1.repository.OrderItemRepository;
import dsg.unibamberg.assignment1.service.IOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService implements IOrderItemService {

    private final OrderItemRepository repository;

    @Autowired
    public OrderItemService(OrderItemRepository repository) {
        this.repository = repository;
    }


    @Override
    public OrderItem create(Beverage beverage, int position) {
        if (beverage == null) {
            return null;
        }
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(1);
        orderItem.setPrice(beverage.getPrice());
        orderItem.setBeverageOrderItem(beverage);
        orderItem.setPosition(position);
        return orderItem;
    }

    @Override
    public List<OrderItem> save(List<OrderItem> orderItems) {
        return this.repository.saveAll(orderItems);
    }
}
