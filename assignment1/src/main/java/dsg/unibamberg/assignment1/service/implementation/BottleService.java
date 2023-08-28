package dsg.unibamberg.assignment1.service.implementation;

import dsg.unibamberg.assignment1.converter.BottleModelMapper;
import dsg.unibamberg.assignment1.form.AddBottleForm;
import dsg.unibamberg.assignment1.model.Bottle;
import dsg.unibamberg.assignment1.repository.BeverageRepository;
import dsg.unibamberg.assignment1.repository.BottleRepository;
import dsg.unibamberg.assignment1.service.IBottleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BottleService implements IBottleService {
    private final BottleRepository bottleRepository;
    private final BeverageRepository beverageRepository;

    @Autowired
    public BottleService(BottleRepository repository, BeverageRepository beverageRepository) {
        this.bottleRepository = repository;
        this.beverageRepository = beverageRepository;
    }

    @Override
    public List<Bottle> findAll() {
        return this.bottleRepository.findAll();
    }

    @Override
    public Bottle findById(Long bottleId) {
        if (bottleId == null) {
            return null;
        }
        return this.bottleRepository.findById(bottleId).orElse(null);
    }

    @Override
    public Bottle createBottle(AddBottleForm form) throws IllegalArgumentException {
        try {
            // Adding static picture for now, will be updated later
            form.setPicture("https://i.postimg.cc/fbg3Btvs/coca-cola.jpg");
            Bottle bottle = BottleModelMapper.fromBottleForm(form);
            this.beverageRepository.save(bottle.getBeverage());
            return this.bottleRepository.save(bottle);

        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw exception;
        }
    }
}
