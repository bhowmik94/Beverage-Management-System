package dsg.unibamberg.assignment1.service;

import dsg.unibamberg.assignment1.form.AddCrateForm;
import dsg.unibamberg.assignment1.model.Crate;

import java.util.List;

public interface ICrateService {
    List<Crate> findAll();

    public Crate findById(Long crateId);

    Crate createCrate(AddCrateForm form) throws IllegalArgumentException;
    
}
