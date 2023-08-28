package dsg.unibamberg.assignment1.service;

import dsg.unibamberg.assignment1.model.Beverage;
import dsg.unibamberg.assignment1.model.OrderItem;

import java.util.List;

public interface IOrderItemService {
    OrderItem create(Beverage beverage, int position);

    List<OrderItem> save(List<OrderItem> orderItems);
}

