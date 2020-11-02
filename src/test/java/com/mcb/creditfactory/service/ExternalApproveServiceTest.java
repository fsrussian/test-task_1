package com.mcb.creditfactory.service;

import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.service.car.CarAdapter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExternalApproveServiceTest {

    @Autowired
    private ExternalApproveService externalApproveService;
    
    @Test
    public void successApproveTest() {
        CarDto carDto = new CarDto(19L, "someBrand", "someModel", 2200.00, (short) 2020,
                BigDecimal.valueOf(5000000.00), LocalDate.of(2019, 07, 15));
        int result = externalApproveService.approve(new CarAdapter(carDto));
        Assert.assertEquals(0, result);
    }

    @Test
    public void tooOldCarApproveTest() {
        CarDto carDto = new CarDto(19L, "someBrand5", "someModel", 2000.00, (short) 1999,
                BigDecimal.valueOf(5000000.00), LocalDate.of(2019, 07, 15));
        int result = externalApproveService.approve(new CarAdapter(carDto));
        Assert.assertEquals(-10, result);
    }

    @Test
    public void tooMinAccessDateApproveTest() {
        CarDto carDto = new CarDto(19L, "someBrand", "someModel", 2015.00, (short) 2015,
                BigDecimal.valueOf(900000000.00), LocalDate.of(2015, 07, 15));
        int result = externalApproveService.approve(new CarAdapter(carDto));
        Assert.assertEquals(-11, result);
    }

    @Test
    public void tooCheapCarApproveTest() {
        CarDto carDto = new CarDto(19L, "someBrand", "someModel", 2000.00, (short) 2001,
                BigDecimal.valueOf(9000), LocalDate.of(2019, 07, 15));
        int result = externalApproveService.approve(new CarAdapter(carDto));
        Assert.assertEquals(-12, result);
    }
    

}
