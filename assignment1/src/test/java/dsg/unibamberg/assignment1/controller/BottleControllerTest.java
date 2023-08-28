package dsg.unibamberg.assignment1.controller;

import dsg.unibamberg.assignment1.form.AddBottleForm;
import dsg.unibamberg.assignment1.model.Bottle;
import dsg.unibamberg.assignment1.service.IBottleService;
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

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(value = BottleController.class, properties = "spring.main.lazy-initialization=true")
public class BottleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    IBottleService bottleService;

    @Test
    public void noParameter_whenAddBottles_thenReturnSuccess() throws Exception {
        Mockito.when(bottleService.findAll()).thenReturn(new ArrayList<>());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/bottles"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void noParameter_whenAddBottleForm_thenReturnSuccess() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/bottles/add"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void givenAddBottleForm_whenPostAddBottleForm_thenReturnSuccess() throws Exception {
        String name = "Some_name";
        String picture = "Some_url_of_the_image";
        Double price = 5.0;
        Double alcoholPercentage = 2.0;
        Double volume = 1.0;
        String supplier= "Some_supplier's_name";
        Integer stock = 10;
        AddBottleForm addBottleForm = new AddBottleForm(name, picture, price, alcoholPercentage, volume, supplier, stock);
        String json = addBottleForm.toJson();

        Mockito.when(bottleService.createBottle(any(AddBottleForm.class))).thenReturn(new Bottle());

        this.mockMvc.perform(MockMvcRequestBuilders.post("/bottles/add").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
