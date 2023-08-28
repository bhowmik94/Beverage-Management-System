package dsg.unibamberg.assignment1.controller;

import dsg.unibamberg.assignment1.form.RegisterForm;
import dsg.unibamberg.assignment1.service.implementation.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping(value = "/register")
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping

    public String getRegister(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }

    @PostMapping
    public String postRegistrationForm(@Valid RegisterForm registrationForm, BindingResult result) {

        if (result.hasErrors()) {
            log.info("User registration contained errors: " + registrationForm.toString());
            return "register";
        }
        if (userService.findByUserName(registrationForm.getEmail()) != null) {
            String objectName = "registrationForm";
            String field = "email";
            String message = "An account already exists for this email.";
            FieldError error = new FieldError(objectName, field, message);
            result.addError(error);
            return "register";
        }

        try {
            userService.createUser(registrationForm);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return "register";
        }

        return "redirect:/login";
    }

}
