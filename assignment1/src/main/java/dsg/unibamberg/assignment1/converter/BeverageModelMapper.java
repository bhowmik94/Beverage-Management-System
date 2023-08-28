package dsg.unibamberg.assignment1.converter;

import dsg.unibamberg.assignment1.form.AddBottleForm;
import dsg.unibamberg.assignment1.form.AddCrateForm;
import dsg.unibamberg.assignment1.model.Beverage;

public class BeverageModelMapper {
    public static Beverage fromBottleForm(AddBottleForm form) {
        Beverage beverage = new Beverage();
        beverage.setName(form.getName());
        beverage.setPicture(form.getPicture());
        beverage.setPrice(form.getPrice());
        beverage.setAlcoholPercent(form.getAlcoholPercentage());
        return beverage;
    }

    public static Beverage fromAddCrateForm(AddCrateForm addCrateForm) {
        Beverage beverage = new Beverage();
        beverage.setName(addCrateForm.getName());
        beverage.setPicture(addCrateForm.getBeveragePic());
        beverage.setPrice(addCrateForm.getPrice());
        beverage.setAlcoholPercent(addCrateForm.getAlcoholPercentage());
        return beverage;
    }

}
