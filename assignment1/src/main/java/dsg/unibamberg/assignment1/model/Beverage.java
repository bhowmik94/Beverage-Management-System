package dsg.unibamberg.assignment1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "beverages")
public class Beverage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "beverage_id", nullable = false)
    private Long id;

    @NotNull(message = "Name can not be null, please provide a value")
    @NotEmpty(message = "Name can't be empty. Please give a proper value")
    @Column(name = "name")
    private String name;

    // Source: https://stackoverflow.com/questions/4098415/use-regex-to-get-image-url-in-html-js
    @Pattern(regexp = "(https?:\\/\\/.*\\.(?:png|jpg))", message = "Please provide a valid image url..")
    @Column(name = "picture")
    private String picture;

    @Min(value = 1, message = "Price must be positive")
    @Column(name = "price")
    private Double price;

    @Column(name = "alcohol_percent")
    private Double alcoholPercent;

    public boolean isAlcoholic() {
        return alcoholPercent > 0.0;
    }

    // TODO: - Add custom toString / hashcode later..

}
