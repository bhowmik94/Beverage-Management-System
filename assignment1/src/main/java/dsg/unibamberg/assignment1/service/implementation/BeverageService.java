package dsg.unibamberg.assignment1.service.implementation;

import dsg.unibamberg.assignment1.model.Beverage;
import dsg.unibamberg.assignment1.repository.BeverageRepository;
import dsg.unibamberg.assignment1.service.IBeverageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeverageService implements IBeverageService {
    private final BeverageRepository beverageRepository;

    @Autowired
    public BeverageService(BeverageRepository beverageRepository) {
        this.beverageRepository = beverageRepository;
    }

    @Override
    public Beverage findById(Long beverageId) {

        if (beverageId == null) {
            return null;
        }
        return this.beverageRepository.findById(beverageId).orElse(null);
    }
}
