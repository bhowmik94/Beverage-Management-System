package dsg.unibamberg.assignment1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedEntityGraph(name = "Order.orderItems", attributeNodes = {@NamedAttributeNode(value = "orderItems")})
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "price")
    private Double price;

    //MARK: - Need to investigate later.
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<OrderItem> orderItems;

    //MARK: - Need to check entity relationship later 

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public static Order create() {
        Order order = new Order();
        order.setPrice(0.0);
        order.setOrderItems(new HashSet<>());
        return order;
    }

    public OrderItem findOrderItemByBeverageId(Long beverageId) {
        return orderItems.stream()
                .filter(item -> Objects.equals(item.getBeverageOrderItem().getId(), beverageId))
                .findFirst()
                .orElse(null);
    }

    public void removeOrderItem(OrderItem orderItem) {
        orderItems.remove(orderItem);
        Double totalOrderPrice = 0.0;
        for (OrderItem item : getOrderItems()) {
            totalOrderPrice += item.getPrice();
        }
        price = totalOrderPrice;
    }

    public void addOrderItem(OrderItem orderItem) {
        if (orderItems == null) {
            orderItems = new HashSet<>();
        }
        orderItems.add(orderItem);
        price += orderItem.getPrice();
    }


}
