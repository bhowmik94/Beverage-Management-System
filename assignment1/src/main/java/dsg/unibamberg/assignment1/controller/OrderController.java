package dsg.unibamberg.assignment1.controller;

import dsg.unibamberg.assignment1.model.Order;
import dsg.unibamberg.assignment1.service.IOrderService;
import dsg.unibamberg.assignment1.service.ISessionService;
import dsg.unibamberg.assignment1.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/orders")
public class OrderController {

    private final IOrderService orderService;
    private final ISessionService sessionService;

    private final IUserService userService;

    @Autowired
    public OrderController(IOrderService orderService, ISessionService sessionService, IUserService userService) {
        this.orderService = orderService;
        this.sessionService = sessionService;
        this.userService = userService;
    }

    @GetMapping
    public String getOrders(Model model, HttpServletRequest request) {
        String username = request.getUserPrincipal().getName();
        List<Order> orders = this.userService.findOrderByUser(username);
        model.addAttribute("orders", orders);
        return "orders";
    }

    @PostMapping
    public String postOrders(HttpServletRequest request) throws IOException, InterruptedException {
        HttpSession session = request.getSession();
        String userName = request.getUserPrincipal().getName();
        Order order = this.sessionService.getOrderFromSession(session);
        Order databaseOrder = this.userService.createOrder(order, userName);
//        this.orderService.createOrder(order);
        this.sessionService.removeOrder(session);
        return "redirect:/orders";
    }

    @GetMapping("/{orderId}")
    public String getOrderDetails(@PathVariable(value = "orderId") Long orderId, Model model, HttpServletRequest request) {
        Order order = this.orderService.findOrderById(orderId);
        model.addAttribute("order", order);
        return "order_details";
    }

}
