
package dsg.unibamberg.assignment1.controller;


import dsg.unibamberg.assignment1.form.*;
import dsg.unibamberg.assignment1.model.*;
import dsg.unibamberg.assignment1.service.*;
import dsg.unibamberg.assignment1.service.implementation.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(value = RegistrationController.class)
public class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Test
    public void givenNoParameter_whenGetRegistrationForm_thenReturnSuccess() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/register"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void givenRegistrationForm_whenPostRegistrationForm_thenReturnSuccess() throws Exception {
        String password = "Some_passwords";
        String fullname = "Some_name";
        String email = "Some_email";
        String street = "Some_strasse";
        String houseNumber = "Some_house_number";
        String postalCode = "Some_postal_Code";
        String city = "Some_city";
        String state = "Some_Region";
        //RegisterForm registerForm = new RegisterForm(fullname, email, password, password, street, houseNumber, postalCode, city, state);
        RegisterForm registerForm = new RegisterForm();
        String json = registerForm.toJson();

        Mockito.when(userService.findByUserName(any(String.class))).thenReturn(null);
        Mockito.when(userService.createUser(any(RegisterForm.class))).thenReturn(new User());

        this.mockMvc.perform(MockMvcRequestBuilders.post("/register").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

