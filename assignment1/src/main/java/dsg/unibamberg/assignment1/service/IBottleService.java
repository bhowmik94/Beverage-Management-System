package dsg.unibamberg.assignment1.service;

import dsg.unibamberg.assignment1.form.AddBottleForm;
import dsg.unibamberg.assignment1.model.Bottle;

import java.io.IOException;
import java.util.List;

public interface IBottleService {
    List<Bottle> findAll();

    public Bottle findById(Long bottleId);

    Bottle createBottle(AddBottleForm form) throws IOException;

}
