package dsg.unibamberg.assignment1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bottles")
public class Bottle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bottle_id")
    private Long id;

    //TODO: - Refactor the commented item later.

//    @NotNull(message = "Name can not be null, please provide a value")
//    @NotEmpty(message = "Name can't be empty. Please give a proper value")
//    @Pattern( regexp = "^[a-zA-Z0-9]+$",message = "Name consist only letter and number.")
//    @Column(name = "name")
//    private String name;

//    // Source: https://stackoverflow.com/questions/4098415/use-regex-to-get-image-url-in-html-js
//    @Pattern(regexp = "(https?:\\/\\/.*\\.(?:png|jpg))\n", message = "Please provide a valid image url..")
//    @Column(name = "picture")
//    private String picture;

    @Positive(message = "Volume should be greater than zero. ")
    @Column(name = "volume")
    private Double volume;

//    @Column(name = "is_alcoholic")
//    private Boolean isAlcoholic;

//    @Column(name = "volume_percent")
//    private Double volumePercent;

//    @Min(value = 1, message = "Price must be positive")
//    @Column(name = "price")
//    private Integer price;

    @NotNull(message = "Supplier Name can not be null, please provide a value")
    @NotEmpty(message = "Supplier Name can't be empty. Please give a proper value")
    @Column(name = "supplier")
    private String supplier;

    @Min(value = 0, message = "Bottle in stock must be positive or zero")
    @Column(name = "stock")
    private Integer stock;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "beverage_id")
    private Beverage beverage;
    // TODO: - Add to String and hashcode later
}
