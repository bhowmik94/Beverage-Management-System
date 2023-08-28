package dsg.unibamberg.assignment1.service.implementation;

import dsg.unibamberg.assignment1.form.AddToCartForm;
import dsg.unibamberg.assignment1.model.Beverage;
import dsg.unibamberg.assignment1.model.Order;
import dsg.unibamberg.assignment1.model.OrderItem;
import dsg.unibamberg.assignment1.service.IBeverageService;
import dsg.unibamberg.assignment1.service.IOrderItemService;
import dsg.unibamberg.assignment1.service.ISessionService;
import dsg.unibamberg.assignment1.utility.AppConstants;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService implements ISessionService {

    private final IBeverageService beverageService;

    private final IOrderItemService orderItemService;

    @Autowired
    public SessionService(IBeverageService beverageService, IOrderItemService orderItemService) {
        this.beverageService = beverageService;
        this.orderItemService = orderItemService;
    }

    @Override
    public Order addOrderItem(HttpSession httpSession, AddToCartForm addToCartForm) {
//        List<OrderItem> orderItemList = fetchOrderFromSessions(httpSession);
//        orderItemList.add(addToCartForm);
//        httpSession.setAttribute(AppConstants.SessionKey.cartItem.toString(), orderItemList);
        Order order = getOrder(httpSession);
        OrderItem orderItem = create(addToCartForm, order);

        order.addOrderItem(orderItem);
        httpSession.setAttribute(String.valueOf(AppConstants.SessionKey.order), order);
        return order;
    }

    @Override
    public Order getOrderFromSession(HttpSession session) {
        return this.getOrder(session);
    }

    @Override
    public void removeOrder(HttpSession session) {
        session.removeAttribute(String.valueOf(AppConstants.SessionKey.order));
    }

//TODO: - May need this method later 

//    public List<OrderItem> fetchOrderFromSessions(HttpSession session) {
//        List<OrderItem> items = (List<OrderItem>) session.getAttribute(AppConstants.SessionKey.cartItem.toString());
//        if (items == null) {
//            return new ArrayList<>();
//        }
//        return items;
//    }

    private Order getOrder(HttpSession session) {
        Order order = (Order) session.getAttribute(String.valueOf(AppConstants.SessionKey.order));
        if (order == null) {
            order = Order.create();
        }
        return order;
    }

    private OrderItem create(AddToCartForm addToCartForm, Order order) {
        Beverage beverage = this.beverageService.findById(addToCartForm.getBeverageId());
        OrderItem orderItem = order.findOrderItemByBeverageId(beverage.getId());
        if (orderItem != null) {
            order.removeOrderItem(orderItem);
            orderItem.increaseQuantity();
            return orderItem;
        }
        return this.orderItemService.create(beverage, order.getOrderItems().size() + 1);
    }
}
