package dsg.unibamberg.assignment1.service;

import dsg.unibamberg.assignment1.model.Beverage;

public interface IBeverageService {

    Beverage findById(Long beverageId);
}
