package dsg.unibamberg.assignment1.form;

import com.google.gson.Gson;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterForm implements Serializable {


    @NotNull(message = "Full name can not be null")
    @NotEmpty(message = "Full name can not be empty")
    private String fullName;

    @NotNull(message = "Email can not be null")
    @NotEmpty(message = "Email can not be empty")
    @Email
    private String email;


    @NotNull(message = "Password can not be null")
    @NotEmpty(message = "Password can not be empty")
    @Size(min = 8, max = 20)
    private String password;

    @NotNull(message = "Retype password can not be null")
    @NotEmpty(message = "Retype password cannot be empty")
    private String retypePassword;


    @NotNull(message = "Street can not be null")
    @NotEmpty(message = "Street can not be empty")
    private String street;

    @NotNull(message = "House number can not be null")
    @NotEmpty(message = "House number can not be empty")
    private String houseNumber;

    @NotNull(message = "Postal code can not be null")
    @NotEmpty(message = "Postal code can not be empty")
    @Size(min = 5, max = 5)
    private String postalCode;

    @NotNull(message = "City can not be null")
    @NotEmpty(message = "City can not be empty")
    private String city;

    @NotNull(message = "State can not be null")
    @NotEmpty(message = "State can not be empty")
    private String state;

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
