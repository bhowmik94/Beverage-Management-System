package dsg.unibamberg.assignment1.controller;

import dsg.unibamberg.assignment1.form.AddToCartForm;
import dsg.unibamberg.assignment1.model.Order;
import dsg.unibamberg.assignment1.service.ISessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/cart")
public class CartController {

    private final ISessionService sessionService;

    @Autowired
    public CartController(ISessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping
    public String getCart(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Order order = this.sessionService.getOrderFromSession(session);
        model.addAttribute("order", order);
        return "cart";
    }

    @PostMapping("/add")
    public String postCart(@RequestParam("origin") String origin, @Valid AddToCartForm addToCartForm, HttpServletRequest request) {
        HttpSession session = request.getSession();
        this.sessionService.addOrderItem(session, addToCartForm);
        return String.format("redirect:/%s", origin);
    }

}
