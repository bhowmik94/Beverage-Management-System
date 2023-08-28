package dsg.unibamberg.assignment1.service;

import dsg.unibamberg.assignment1.form.AddCrateForm;
import dsg.unibamberg.assignment1.model.Beverage;
import dsg.unibamberg.assignment1.model.Bottle;
import dsg.unibamberg.assignment1.model.Crate;
import dsg.unibamberg.assignment1.repository.BeverageRepository;
import dsg.unibamberg.assignment1.repository.CrateRepository;
import dsg.unibamberg.assignment1.service.implementation.BottleService;
import dsg.unibamberg.assignment1.service.implementation.CrateService;
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
public class CrateServiceTest {

    @InjectMocks
    private CrateService crateService;

    @Mock
    private CrateRepository mockCrateRepository;

    @Mock
    private BeverageRepository mockBeverageRepository;

    @Mock
    private BottleService mockBottleService;

    //@Mock
    //private CloudStorageServiceImplementation mockCloudStorageService;

    @Test
    public void givenId_whenFindById_thenReturnItem() {
        long crateId = 1;
        Beverage beverage = new Beverage();
        Bottle bottle = new Bottle();
        Crate expectedCrate = new Crate(crateId, 20, 10, bottle, beverage);
        Mockito.when(mockCrateRepository.findById(crateId)).thenReturn(Optional.of(expectedCrate));

        Crate actualCrate = crateService.findById(crateId);
        Assertions.assertEquals(expectedCrate, actualCrate);
    }

    @Test
    public void givenCrateInfo_whenCreateCrate_thenReturnCrate() throws IOException {
        String name = "Some drinks";
        String picture = "https://xyz,com/drinks.png";
        Double price = 123.0;
        Double alcoholPercentage = 0.0;
        Double volume = 123.0;
        Integer stock = 123;
        Integer noOfBottles = 123;
        Integer crateInStock = 123;
        long id = 1;
        long bottleID = id;
        String beveragePic = picture;
        //MockMultipartFile file = new MockMultipartFile("New file", new byte[0]);
        Beverage beverage = new Beverage(id, name, picture, price, alcoholPercentage);
        Bottle bottle = new Bottle(id, volume, name, stock, beverage);
        Crate expectedCrate = new Crate(id, noOfBottles, crateInStock, bottle, beverage);
        Mockito.when(mockBeverageRepository.save(any(Beverage.class))).thenReturn((beverage));
        Mockito.when(mockBottleService.findById(any(Long.class))).thenReturn(bottle);
        Mockito.when(mockCrateRepository.save(any(Crate.class))).thenReturn((expectedCrate));
        //Mockito.when(mockCloudStorageService.uploadImage(any(MultipartFile.class))).thenReturn(beveragePic);

        AddCrateForm addCrateForm = new AddCrateForm(bottleID, name, beveragePic, price, alcoholPercentage, noOfBottles, stock);
        Crate actualCrate = crateService.createCrate(addCrateForm);
        Assertions.assertNotNull(actualCrate);
        Assertions.assertEquals(expectedCrate.getCreateId(), actualCrate.getCreateId());
        Assertions.assertEquals(expectedCrate.getNoOfBottles(), actualCrate.getNoOfBottles());
        Assertions.assertEquals(expectedCrate.getCratesInStock(), actualCrate.getCratesInStock());
        Assertions.assertEquals(expectedCrate.getBottle().getId(), actualCrate.getBottle().getId());
        Assertions.assertEquals(expectedCrate.getBottle().getVolume(), actualCrate.getBottle().getVolume());
        Assertions.assertEquals(expectedCrate.getBottle().getStock(), actualCrate.getBottle().getStock());
        Assertions.assertEquals(expectedCrate.getBottle().getSupplier(), actualCrate.getBottle().getSupplier());
        Assertions.assertEquals(expectedCrate.getBottle().getVolume(), actualCrate.getBottle().getVolume());
        Assertions.assertEquals(expectedCrate.getBeverage().getName(), actualCrate.getBeverage().getName());
        Assertions.assertEquals(expectedCrate.getBeverage().getId(), actualCrate.getBeverage().getId());
        Assertions.assertEquals(expectedCrate.getBeverage().getName(), actualCrate.getBeverage().getName());
        Assertions.assertEquals(expectedCrate.getBeverage().getPicture(), actualCrate.getBeverage().getPicture());
        Assertions.assertEquals(expectedCrate.getBeverage().getPrice(), actualCrate.getBeverage().getPrice());
        Assertions.assertEquals(expectedCrate.getBeverage().getAlcoholPercent(), actualCrate.getBeverage().getAlcoholPercent());

    }
}
