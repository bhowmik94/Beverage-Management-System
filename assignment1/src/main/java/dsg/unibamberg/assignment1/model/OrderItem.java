package dsg.unibamberg.assignment1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_items")
public class OrderItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_item_id")
    private Long orderItemId;

    @Column(name = "position")
    private Integer position;

    @Positive(message = "Price must be greater than zero.")
    @Column(name = "price")
    private double price;

    @Positive(message = "Quantity must be greater than zero.")
    @Column(name = "quantity")
    private Integer quantity;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "beverage_id")
    private Beverage beverageOrderItem;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Order order;

    public void increaseQuantity() {
        setQuantity(getQuantity() + 1);
        setPrice(getBeverageOrderItem().getPrice() * getQuantity());
    }

}
