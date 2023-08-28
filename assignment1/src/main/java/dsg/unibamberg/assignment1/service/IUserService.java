package dsg.unibamberg.assignment1.service;


import dsg.unibamberg.assignment1.form.RegisterForm;
import dsg.unibamberg.assignment1.model.Order;
import dsg.unibamberg.assignment1.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    User createUser(RegisterForm registrationForm) throws IllegalArgumentException;

    User findByUserName(String username);

    void createDefaultUser();

    List<User> findAllUsers();

    String findUsernameByUserId(String userId);

    public Order createOrder(Order order, String username);

    public List<Order> findOrderByUser(String username);

}
