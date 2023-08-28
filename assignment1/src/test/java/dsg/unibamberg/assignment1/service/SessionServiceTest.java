
package dsg.unibamberg.assignment1.service;


import dsg.unibamberg.assignment1.form.AddToCartForm;
import dsg.unibamberg.assignment1.model.*;
import dsg.unibamberg.assignment1.service.implementation.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.mock.web.MockHttpSession;

import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class SessionServiceTest {
    @InjectMocks
    private SessionService sessionService;

    @Mock
    private BeverageService mockBeverageService;

    @Mock
    private OrderItemService mockOrderItemService;

    @Test
    public void givenFormAndOrder_whenAddItemToOrder_thenReturnOrder() {
        String name = "Some drinks";
        String picture = "https://xyz.com/drinks.png";
        Double price = 10.0;
        Double alcoholPercentage = 0.0;
        Long id = 1L;
        String email = "user@mail.com";
        String password = "password";
        String username = "user name";
        String full_name = "full name";
        String street = "street name";
        String houseNumber = "123";
        String postalCode = "12345";
        String cityState = "Bamberg";
        Beverage beverage = new Beverage(id, name, picture, price, alcoholPercentage);
        User user = new User(id, username,password, full_name, email, "CUSTOMER", new Address(id,street, houseNumber, postalCode,cityState,cityState), new HashSet<>());
        Order order = new Order(id, 2.0, new HashSet<>(), user);
        OrderItem expectedOrderItem = new OrderItem(id, 1, price, 20, beverage, order);

        Mockito.when(mockBeverageService.findById(any(Long.class))).thenReturn(beverage);
        Mockito.when(mockOrderItemService.create(any(Beverage.class), any(Integer.class))).thenReturn(expectedOrderItem);

        AddToCartForm addToCartForm = new AddToCartForm();
        addToCartForm.setBeverageId(id);
        MockHttpSession session = new MockHttpSession();
        //session.setAttribute(SessionConstant.ORDER, order);
        Order actualOrder = sessionService.addOrderItem(session, addToCartForm);

        Assertions.assertNotNull(actualOrder);
        //Assertions.assertEquals(expectedOrderItem.getOrder().getOrderId(), actualOrder.getOrderId());

    }

}

