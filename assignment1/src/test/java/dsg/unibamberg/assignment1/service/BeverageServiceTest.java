package dsg.unibamberg.assignment1.service;

import dsg.unibamberg.assignment1.model.Beverage;
import dsg.unibamberg.assignment1.repository.BeverageRepository;
import dsg.unibamberg.assignment1.service.implementation.BeverageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class BeverageServiceTest {

    @InjectMocks
    private BeverageService beverageService;

    @Mock
    private BeverageRepository mockBeverageRepository;

    @Test
    public void givenId_whenFindById_thenReturnInfo() {
        long beverageId = 10;
        Beverage expectedBeverage = new Beverage(beverageId, "Some_name", "Some_url", 5.0, 10.0);
        Mockito.when(mockBeverageRepository.findById(any(Long.class))).thenReturn(Optional.of(expectedBeverage));

        Beverage actualBeverage = beverageService.findById(beverageId);
        Assertions.assertNotNull(actualBeverage);
        Assertions.assertEquals(expectedBeverage.getId(), actualBeverage.getId());
        Assertions.assertEquals(expectedBeverage.getName(), actualBeverage.getName());
        Assertions.assertEquals(expectedBeverage.getPicture(), actualBeverage.getPicture());
        Assertions.assertEquals(expectedBeverage.getPrice(), actualBeverage.getPrice());
        Assertions.assertEquals(expectedBeverage.getAlcoholPercent(), actualBeverage.getAlcoholPercent());

    }
}

