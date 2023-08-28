package dsg.unibamberg.assignment1.converter;


import dsg.unibamberg.assignment1.form.RegisterForm;
import dsg.unibamberg.assignment1.model.User;
import dsg.unibamberg.assignment1.utility.AppConstants;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

public class UserModelMapper {
    public static User fromRegistrationForm(RegisterForm registerForm, PasswordEncoder passwordEncoder) {
        return fromRegistrationForm(registerForm, passwordEncoder, AppConstants.Roles.customer.name());
    }

    public static User fromRegistrationForm(RegisterForm registerForm, PasswordEncoder passwordEncoder, String role) {
        User user = new User();
        user.setFullName(registerForm.getFullName());
        user.setUsername(registerForm.getEmail());
        user.setPassword(passwordEncoder.encode(registerForm.getPassword()));
        user.setEmail(registerForm.getEmail());
        user.setRole(role);
        user.setAddress(AddressModelMapper.fromRegistrationForm(registerForm));
        user.setOrders(new HashSet<>());
        return user;
    }

    public static User fromEnvironment(Environment environment, PasswordEncoder passwordEncoder) {
        User user = new User();
        user.setFullName(environment.getProperty("user.default.fullName"));
        user.setUsername(environment.getProperty("user.default.email"));
        user.setPassword(passwordEncoder.encode(environment.getProperty("user.default.password")));
        user.setEmail(environment.getProperty("user.default.email"));
        user.setRole(AppConstants.Roles.admin.name().toUpperCase());
        user.setAddress(AddressModelMapper.fromEnvironment(environment));
        user.setOrders(new HashSet<>());
        return user;
    }
}