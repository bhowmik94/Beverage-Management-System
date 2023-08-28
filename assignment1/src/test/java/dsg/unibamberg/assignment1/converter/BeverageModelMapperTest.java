package dsg.unibamberg.assignment1.converter;

import dsg.unibamberg.assignment1.form.AddBottleForm;
import dsg.unibamberg.assignment1.form.AddCrateForm;
import dsg.unibamberg.assignment1.model.Beverage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BeverageModelMapperTest {

    @Test
    public void givenAddBottleForm_returnCorrectBeverage() {
        /// Arrange 
        AddBottleForm form = new AddBottleForm();
        form.setName("A");
        form.setPrice(1.0);
        form.setStock(10);
        form.setVolume(125.0);
        form.setSupplier("Test supplier");
        form.setAlcoholPercentage(5.0);
        form.setPicture("https://i.postimg.cc/fbg3Btvs/coca-cola.jpg");

        /// Act 
        Beverage beverage = BeverageModelMapper.fromBottleForm(form);

        /// Assert 

        Assertions.assertEquals(beverage.getName(), form.getName());
        Assertions.assertEquals(beverage.getPrice(), form.getPrice());

        Assertions.assertEquals(beverage.getPicture(), form.getPicture());
        Assertions.assertEquals(beverage.getAlcoholPercent(), form.getAlcoholPercentage());

    }

    @Test
    public void givenAddCrateForm_returnCorrectBeverage() {
        /// Arrange
        AddCrateForm form = new AddCrateForm();
        form.setName("A");
        form.setPrice(1.0);
        form.setBeveragePic("https://i.postimg.cc/fbg3Btvs/coca-cola.jpg");
        form.setAlcoholPercentage(5.0);

        /// Act
        Beverage beverage = BeverageModelMapper.fromAddCrateForm(form);

        /// Assert

        Assertions.assertEquals(beverage.getName(), form.getName());
        Assertions.assertEquals(beverage.getPrice(), form.getPrice());

        Assertions.assertEquals(beverage.getPicture(), form.getBeveragePic());
        Assertions.assertEquals(beverage.getAlcoholPercent(), form.getAlcoholPercentage());

    }
}
