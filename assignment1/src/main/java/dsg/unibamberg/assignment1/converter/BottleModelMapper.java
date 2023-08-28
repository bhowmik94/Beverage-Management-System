package dsg.unibamberg.assignment1.converter;

import dsg.unibamberg.assignment1.form.AddBottleForm;
import dsg.unibamberg.assignment1.model.Bottle;

public class BottleModelMapper {
    public static Bottle fromBottleForm(AddBottleForm form) {
        Bottle bottle = new Bottle();
        bottle.setStock(form.getStock());
        bottle.setVolume(form.getVolume());
        bottle.setSupplier(form.getSupplier());
        bottle.setBeverage(BeverageModelMapper.fromBottleForm(form));
        return bottle;
    }
}
