package dsg.unibamberg.assignment1.controller;

import dsg.unibamberg.assignment1.form.AddCrateForm;
import dsg.unibamberg.assignment1.model.Crate;
import dsg.unibamberg.assignment1.service.ICrateService;
import dsg.unibamberg.assignment1.service.implementation.BottleService;
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
@WebMvcTest(value = CrateController.class, properties = "spring.main.lazy-initialization=true")
public class CrateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ICrateService cratesService;

    @MockBean
    BottleService bottleService;

    @Test
    public void noParameter_whenGetCrates_thenReturnSuccess() throws Exception {
        Mockito.when(cratesService.findAll()).thenReturn(new ArrayList<>());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/crates"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void noParameter_whenShowAddCrate_thenReturnSuccess() throws Exception {
        Mockito.when(bottleService.findAll()).thenReturn(new ArrayList<>());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/crates/add"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void givenAddCrateForm_whenPostAddCrateForm_thenReturnSuccess() throws Exception {
        long bottleID = 1;
        String name = "Some_name";
        String beveragePic = "Some_url_for_image";
        Double price = 3.0;
        Double alcoholPercentage = 1.0;
        Integer noOfBottles = 5;
        Integer stock = 5;
        AddCrateForm addCrateForm = new AddCrateForm(bottleID, name, beveragePic, price, alcoholPercentage, noOfBottles, stock);
        String json = addCrateForm.toJson();

        Mockito.when(cratesService.createCrate(any(AddCrateForm.class))).thenReturn(new Crate());

        this.mockMvc.perform(MockMvcRequestBuilders.post("/crates/add").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
