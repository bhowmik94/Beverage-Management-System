package dsg.unibamberg.assignment1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "crates")
public class Crate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "crate_id")
    private Long createId;

//    @NotNull(message = "Name can not be null, please provide a value")
//    @NotEmpty(message = "Name can't be empty. Please give a proper value")
//    @Column(name = "name")
//    private String name;

    // Source: https://stackoverflow.com/questions/4098415/use-regex-to-get-image-url-in-html-js
//    @Pattern(regexp = "(https?:\\/\\/.*\\.(?:png|jpg))\n", message = "Please provide a valid image url..")
//    @Column(name = "picture")
//    private String picture;

    @Column(name = "no_of_bottles")
    private int noOfBottles;

//    @Column(name = "price")
//    private double price;

    @Column(name = "creates_in_stock")
    private int cratesInStock;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bottle_id")
    private Bottle bottle;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "crate_beverage_id")
    private Beverage beverage;


}
