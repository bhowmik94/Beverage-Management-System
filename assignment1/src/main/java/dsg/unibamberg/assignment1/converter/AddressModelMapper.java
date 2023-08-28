package dsg.unibamberg.assignment1.converter;

import dsg.unibamberg.assignment1.form.RegisterForm;
import dsg.unibamberg.assignment1.model.Address;
import org.springframework.core.env.Environment;

public class AddressModelMapper {
    public static Address fromRegistrationForm(RegisterForm registerForm) {
        Address address = new Address();
        address.setStreet(registerForm.getStreet());
        address.setHouseNumber(registerForm.getHouseNumber());
        address.setPostalCode(registerForm.getPostalCode());
        address.setCity(registerForm.getCity());
        address.setState(registerForm.getState());
        return address;
    }

    public static Address fromEnvironment(Environment environment) {
        Address address = new Address();
        address.setStreet(environment.getProperty("user.default.street"));
        address.setHouseNumber(environment.getProperty("user.default.houseNumber"));
        address.setPostalCode(environment.getProperty("user.default.postalCode"));
        address.setCity(environment.getProperty("user.default.city"));
        address.setState(environment.getProperty("user.default.state"));
        return address;
    }
}
