package dsg.unibamberg.assignment1.service.implementation;

import dsg.unibamberg.assignment1.converter.UserModelMapper;
import dsg.unibamberg.assignment1.form.RegisterForm;
import dsg.unibamberg.assignment1.model.Address;
import dsg.unibamberg.assignment1.model.Order;
import dsg.unibamberg.assignment1.model.User;
import dsg.unibamberg.assignment1.repository.AddressRepository;
import dsg.unibamberg.assignment1.repository.OrderRepository;
import dsg.unibamberg.assignment1.repository.UserRepository;
import dsg.unibamberg.assignment1.service.IUserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AddressRepository addressRepository;

    private final Environment environment;

    private final OrderRepository orderRepository;

    private final OrderService orderService;


    @Autowired
    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder, AddressRepository addressRepository, Environment environment, OrderRepository orderRepository, OrderService orderService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.addressRepository = addressRepository;
        this.environment = environment;
        this.orderRepository = orderRepository;
        this.orderService = orderService;
    }


    @Override
    public User createUser(RegisterForm registrationForm) throws IllegalArgumentException {
        try {
            User user = UserModelMapper.fromRegistrationForm(registrationForm, passwordEncoder);
            Address address = user.getAddress();
            addressRepository.save(address);
            return userRepository.save(user);
        } catch (IllegalArgumentException exception) {
            log.error("Exception occurred" + exception.getLocalizedMessage());
            throw exception;
        }
    }

    @Override
    public User findByUserName(String username) {
        return this.userRepository.findUserByUsername(username);
    }

    @Override
    public void createDefaultUser() {
        if (!this.userRepository.findAll().isEmpty()) {
            return;
        }

        User user = UserModelMapper.fromEnvironment(environment, passwordEncoder);
        addressRepository.save(user.getAddress());
        userRepository.save(user);
        log.info("createDefaultUser executed");
    }

    @Override
    public List<User> findAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public String findUsernameByUserId(String userId) {
        User user = this.userRepository.findById(Long.parseLong(userId)).orElse(null);

        return user != null && !user.getUsername().isBlank() ? user.getUsername() : "";

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username);
    }


    @Transactional
    public Order createOrder(Order order, String username) {

        User user = this.userRepository.findUserByUsername(username);
        Order databaseOrder = this.orderRepository.save(order);

        Set<Order> orders = user.getOrders();
        orders.add(databaseOrder);
        user.setOrders(orders);
        this.userRepository.save(user);

        return databaseOrder;
    }

    @Transactional
    public List<Order> findOrderByUser(String username) {
        User user = this.userRepository.findUserByUsername(username);
        if (user.getRole().equals("ADMIN")) {
            return this.orderService.findAll();
        }
        Set<Order> userOrders = this.userRepository.findUserByUsername(username).getOrders();
        return new ArrayList<>(userOrders);
    }

}
