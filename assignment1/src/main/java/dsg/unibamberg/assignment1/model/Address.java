package dsg.unibamberg.assignment1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "addresses")
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    private Long id;

    @NotNull(message = "Street can not be null.")
    @NotEmpty(message = "Street can't be empty.")
    private String street;


    @NotNull(message = "House number can not be null")
    @NotEmpty(message = "House number can not be empty")
    @Column(name = "house_number")
    private String houseNumber;

    @NotNull(message = "Postal code can not be null.")
    @NotEmpty(message = "Postal code can't be empty.")
    @Size(min = 5, max = 5, message = "Postal code must be five digits.")
    @Column(name = "postal_code")
    private String postalCode;

    @NotNull(message = "City can not be null")
    @NotEmpty(message = "City can not be empty")
    @Column(name = "city")
    private String city;

    @NotNull(message = "State can not be null")
    @NotEmpty(message = "State can not be empty")
    @Column(name = "state")
    private String state;
}
