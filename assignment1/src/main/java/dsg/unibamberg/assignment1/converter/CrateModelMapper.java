package dsg.unibamberg.assignment1.converter;

import dsg.unibamberg.assignment1.form.AddCrateForm;
import dsg.unibamberg.assignment1.model.Bottle;
import dsg.unibamberg.assignment1.model.Crate;

public class CrateModelMapper {
    public static Crate fromAddCrateForm(AddCrateForm addCrateForm, Bottle bottle) {
        Crate crate = new Crate();
        crate.setBottle(bottle);
        crate.setNoOfBottles(addCrateForm.getNoOfBottles());
        crate.setCratesInStock(addCrateForm.getStock());
        crate.setBeverage(BeverageModelMapper.fromAddCrateForm(addCrateForm));
        return crate;
    }
}
