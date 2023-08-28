package dsg.unibamberg.assignment1.service;

import dsg.unibamberg.assignment1.form.AddBottleForm;
import dsg.unibamberg.assignment1.model.Beverage;
import dsg.unibamberg.assignment1.model.Bottle;
import dsg.unibamberg.assignment1.repository.BeverageRepository;
import dsg.unibamberg.assignment1.repository.BottleRepository;
import dsg.unibamberg.assignment1.service.implementation.BottleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.io.IOException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class BottleServiceTest {

    @InjectMocks
    private BottleService bottleService;

    //@Mock
    //private CloudStorageServiceImpl mockCloudStorageService;

    @Mock
    private BottleRepository mockBottleRepository;

    @Mock
    private BeverageRepository mockBeverageRepository;

    @Test
    public void givenId_whenFindById_thenReturnItem() {
        long id = 1;
        Beverage beverage = new Beverage();
        Bottle expectedBottle = new Bottle(id, 0.5, "Pepsi", 20, beverage);
        Mockito.when(mockBottleRepository.findById(id)).thenReturn(Optional.of(expectedBottle));

        Bottle actualBottle = bottleService.findById(id);
        Assertions.assertNotNull(actualBottle);
        Assertions.assertEquals(expectedBottle.getId(), actualBottle.getId());
        Assertions.assertEquals(expectedBottle.getVolume(), actualBottle.getVolume());
        Assertions.assertEquals(expectedBottle.getSupplier(), actualBottle.getSupplier());
        Assertions.assertEquals(expectedBottle.getStock(), actualBottle.getStock());
        Assertions.assertEquals(expectedBottle.getBeverage().getId(), actualBottle.getBeverage().getId());
        Assertions.assertEquals(expectedBottle.getBeverage().getName(), actualBottle.getBeverage().getName());
        Assertions.assertEquals(expectedBottle.getBeverage().getPicture(), actualBottle.getBeverage().getPicture());
        Assertions.assertEquals(expectedBottle.getBeverage().getPrice(), actualBottle.getBeverage().getPrice());
        Assertions.assertEquals(expectedBottle.getBeverage().getAlcoholPercent(), actualBottle.getBeverage().getAlcoholPercent());
        Assertions.assertEquals(expectedBottle.getBeverage().getClass(), actualBottle.getBeverage().getClass());

    }

    @Test
    public void givenBottleInfoAndCreatedBottle_whenCreateBottle_thenReturnBottle() throws IOException {
        long id = 10;
        String name = "Some_bottle_name";
        String picture = "http://xyz.com/pic.png";
        String supplier = "some_supplier";
        Double price = 5.0;
        Double alcoholPercentage = 1.0;
        Double volume = 0.0;
        Integer stock = 12;
        //MockMultipartFile file = new MockMultipartFile("New file", new byte[0]);
        Beverage beverage = new Beverage((long) 123465.0, "Some_name", "http://xyz.com/pic.png", 5.0, 1.0);
        Bottle expectedBottle = new Bottle(id, (double) 10.0, "some_supplier", 110, beverage);
        Mockito.when(mockBeverageRepository.save(any(Beverage.class))).thenReturn((beverage));
        Mockito.when(mockBottleRepository.save(any(Bottle.class))).thenReturn((expectedBottle));
        //Mockito.when(mockCloudStorageService.uploadImage(any(MultipartFile.class))).thenReturn(beveragePic);

        AddBottleForm addBottleForm = new AddBottleForm(name, picture, price, alcoholPercentage, volume, supplier, stock);
        Bottle actualBottle = bottleService.createBottle(addBottleForm);
        Assertions.assertNotNull(actualBottle);
        Assertions.assertEquals(expectedBottle.getId(), actualBottle.getId());
        Assertions.assertEquals(expectedBottle.getVolume(), actualBottle.getVolume());
        Assertions.assertEquals(expectedBottle.getSupplier(), actualBottle.getSupplier());
        Assertions.assertEquals(expectedBottle.getStock(), actualBottle.getStock());
        Assertions.assertEquals(expectedBottle.getBeverage().getId(), actualBottle.getBeverage().getId());
        Assertions.assertEquals(expectedBottle.getBeverage().getName(), actualBottle.getBeverage().getName());
        Assertions.assertEquals(expectedBottle.getBeverage().getPrice(), actualBottle.getBeverage().getPrice());
        Assertions.assertEquals(expectedBottle.getBeverage().getAlcoholPercent(), actualBottle.getBeverage().getAlcoholPercent());
        Assertions.assertEquals(expectedBottle.getBeverage().getPicture(), actualBottle.getBeverage().getPicture());

    }
}
