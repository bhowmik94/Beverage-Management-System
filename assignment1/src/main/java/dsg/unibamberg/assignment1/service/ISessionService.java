package dsg.unibamberg.assignment1.service;

import dsg.unibamberg.assignment1.form.AddToCartForm;
import dsg.unibamberg.assignment1.model.Order;
import jakarta.servlet.http.HttpSession;

public interface ISessionService {
    Order addOrderItem(HttpSession httpSession, AddToCartForm addToCartForm);

    Order getOrderFromSession(HttpSession session);

    void removeOrder(HttpSession session);
}
