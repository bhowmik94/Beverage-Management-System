package dsg.unibamberg.assignment1.service.implementation;

import dsg.unibamberg.assignment1.converter.CrateModelMapper;
import dsg.unibamberg.assignment1.form.AddCrateForm;
import dsg.unibamberg.assignment1.model.Bottle;
import dsg.unibamberg.assignment1.model.Crate;
import dsg.unibamberg.assignment1.repository.BeverageRepository;
import dsg.unibamberg.assignment1.repository.CrateRepository;
import dsg.unibamberg.assignment1.service.IBottleService;
import dsg.unibamberg.assignment1.service.ICrateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CrateService implements ICrateService {
    private final CrateRepository crateRepository;

    private final BeverageRepository beverageRepository;
    private final IBottleService bottleService;

    @Autowired
    public CrateService(CrateRepository repository, BeverageRepository beverageRepository, IBottleService bottleService) {
        this.crateRepository = repository;
        this.beverageRepository = beverageRepository;
        this.bottleService = bottleService;
    }

    @Override
    public List<Crate> findAll() {
        return this.crateRepository.findAll();
    }

    public Crate findById(Long crateId) {
        if (crateId == null) {
            return null;
        }
        return this.crateRepository.findById(crateId).orElse(null);
    }

    @Override
    public Crate createCrate(AddCrateForm form) throws IllegalArgumentException {
        try {
            //TODO:-  Adding static picture for now, will be updated later
            form.setBeveragePic("https://i.postimg.cc/fbg3Btvs/coca-cola.jpg");
            Bottle bottle = this.bottleService.findById(form.getBottleId());
            Crate crate = CrateModelMapper.fromAddCrateForm(form, bottle);
            this.beverageRepository.save(crate.getBeverage());
            return this.crateRepository.save(crate);

        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw exception;
        }
    }
}
