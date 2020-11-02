package com.mcb.creditfactory.service;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.external.ExternalApproveService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CollateralServiceTest {

    @Autowired
    private CollateralService collateralService;
    @Autowired
    ExternalApproveService approveService;

    @Test
    public void test1_success_Save_Car_Collateral_with_null_date() {
        // Добавление нового объекта обеспечения (автомобиль) с пустой датой:
        CarDto carDto = new CarDto(null, "BMV", "X1", 1000.00, (short) 2017,
                BigDecimal.valueOf(7000000.00), null);
        Assert.assertEquals(1, collateralService.saveCollateral(carDto).longValue());

        // Добавление нового объекта обеспечения (автомобиль):
        CarDto carDto2 = new CarDto(null, "BMV", "X3", 1000.00, (short) 2017,
                BigDecimal.valueOf(7000000.00), LocalDate.of(2020, 07, 15));
        Assert.assertEquals(2, collateralService.saveCollateral(carDto2).longValue());

        // Добавление нового объекта обеспечения (автомобиль):
        CarDto carDto3 = new CarDto(null, "mazda", "3", 4000.00, (short) 2020,
                BigDecimal.valueOf(7000000.00), LocalDate.of(2020, 07, 15));
        Assert.assertEquals(3, collateralService.saveCollateral(carDto3).longValue());

    }

    @Test
    public void test2_success_Save_AirplaneCollateral_Test() {
        // Добавление нового объекта обеспечения (самолет) с пустой датой:
        AirplaneDto airplaneDto1 = new AirplaneDto(null, "boeing", "747", "London", (short) 2020,
                100000, 300, BigDecimal.valueOf(290000000), null);
        Assert.assertEquals(1, collateralService.saveCollateral(airplaneDto1).longValue());

        // Добавление нового объекта обеспечения (самолет) с пустой датой:
        AirplaneDto airplaneDto2 = new AirplaneDto(null, "boeing", "747", "London", (short) 2020,
                100000, 300, BigDecimal.valueOf(290000000), LocalDate.of(2020, 07, 15));
        Assert.assertEquals(2, collateralService.saveCollateral(airplaneDto2).longValue());
    }


    @Test
    public void test3_failed_Save_Car_with_low_Value_Collateral() {
        // Добавление нового объекта обеспечения (автомобиль) с низкой величиной стоимостной оценки:
        CarDto carDto = new CarDto(null, "BMV", "X1", 1000.00, (short) 2017,
                BigDecimal.valueOf(100.00), LocalDate.of(2020, 07, 15));
        Assert.assertNull(collateralService.saveCollateral(carDto));

    }

    @Test
    public void test4_failed_Save_Car_with_not_approved_date_Collateral_Test() {
        // Добавление нового объекта обеспечения (автомобиль) с не подходящей датой:
        CarDto carDto = new CarDto(null, "BMV", "X1", 1000.00, (short) 2017,
                BigDecimal.valueOf(1000000.00), LocalDate.of(1950, 07, 15));
        Assert.assertNull(collateralService.saveCollateral(carDto));

    }


    @Test
    public void test5_success_Save_CollateralTest() {
        //Добавление новых стимостных оценок
        // Создание новых объектов обеспечения(автомобили):
        CarDto carDto = new CarDto(1l, "BMV", "X1", 1000.00, (short) 2017,
                BigDecimal.valueOf(7000000.00), null);
        CarDto carDto2 = new CarDto(2l, "Volkswagen", "polo", 4000.00, (short) 2020,
                BigDecimal.valueOf(7000000.00), LocalDate.of(2020, 07, 15));
        CarDto carDto3 = new CarDto(3l, "mazda", "someModel2", 4000.00, (short) 2020,
                BigDecimal.valueOf(7000000.00), LocalDate.of(2020, 07, 15));

        // Добавление новых объектов обеспечения. Возвращаются id добавленных объектов:
        Assert.assertEquals(1, collateralService.saveCollateral(carDto).longValue());
        Assert.assertEquals(2, collateralService.saveCollateral(carDto2).longValue());
        Assert.assertEquals(3, collateralService.saveCollateral(carDto3).longValue());


        // Обновление стоимостной оценки (сумма, дата) для объекта carDto, сохраненного в базе данных под id=1:
        carDto.setId(1l);
        carDto.setValue(BigDecimal.valueOf(9000000));
        carDto.setDate(LocalDate.of(2022, 07, 15));

        // Добавление новой стоимостной оценки для carDto id = 1:
        Assert.assertEquals(1, collateralService.saveCollateral(carDto).longValue());


        // Создание новых объектов обеспечения(самолеты):

        AirplaneDto airplaneDto1 = new AirplaneDto(1l, "boeing", "747", "London", (short) 2020,
                100000, 300, BigDecimal.valueOf(290000000), null);
        AirplaneDto airplaneDto2 = new AirplaneDto(2l, "Airbus", "241", "Paris", (short) 2020,
                100000, 300, BigDecimal.valueOf(250000000), LocalDate.of(2020, 07, 15));
        // добавление новой стоимостной оценки для объекта airplaneDto1 с id=1l
        AirplaneDto airplaneDto3 = new AirplaneDto(1l, "boeing", "747", "London", (short) 2020,
                100000, 300, BigDecimal.valueOf(350000000), LocalDate.of(2022, 07, 15));

        Assert.assertEquals(1, collateralService.saveCollateral(airplaneDto1).longValue());
        Assert.assertEquals(2, collateralService.saveCollateral(airplaneDto2).longValue());
        Assert.assertEquals(1, collateralService.saveCollateral(airplaneDto3).longValue());

    }


    @Test
    public void test6_failedSaveCollateralTest() {
        // Создание новых объектов обеспечения(автомобили) с не подходящими оценочными стоимостями(value, date):
        CarDto carDto1 = new CarDto(null, "someBrand", "someModel1", 2000.00, (short) 2019);
        CarDto carDto2 = new CarDto(null, "someBrand1", "someModel2", 4000.00, (short) 1990,
                BigDecimal.valueOf(7000000.00), LocalDate.of(2020, 07, 15));
        CarDto carDto3 = new CarDto(null, "someBrand2", "someModel3", 4000.00, (short) 1990,
                null, null);
        CarDto carDto4 = new CarDto(null, "someBrand3", "someMode4", 2000.00, (short) 2019);
        CarDto carDto5 = new CarDto(null, "someBrand4", "someModel5", 4000.00, (short) 1970,
                BigDecimal.valueOf(1000.00), LocalDate.of(2020, 07, 15));
        CarDto carDto6 = new CarDto(null, "someBrand1", "someModel1", 4000.00, (short) 1970,
                BigDecimal.valueOf(1000.00), LocalDate.of(2020, 07, 15));

        // Создание новых объектов обеспечения(самолеты) с не подходящими оценочными стоимостями(value, date):
        AirplaneDto airplaneDto1 = new AirplaneDto(null, "some airplane brand", "747", "boeing", (short) 2020,
                100000, 300, BigDecimal.valueOf(10000), null);
        AirplaneDto airplaneDto2 = new AirplaneDto(null, "some airplane brand", "747", "boeing", (short) 2020,
                100000, 300, BigDecimal.valueOf(250000000), LocalDate.of(1960, 07, 15));
        AirplaneDto airplaneDto3 = new AirplaneDto(null, "some airplane brand", "747", "boeing", (short) 2020,
                100000, 300, null, LocalDate.of(2020, 07, 15));
        AirplaneDto airplaneDto4 = new AirplaneDto(null, "some airplane brand", "747", "boeing", (short) 1950,
                100000, 300, BigDecimal.valueOf(270000000), LocalDate.of(2020, 07, 15));

        // Создание новых объектов обеспечения с не подходящими оценочными стоимостями(value, date):
        Assert.assertNull(collateralService.saveCollateral(carDto1));
        Assert.assertNull(collateralService.saveCollateral(carDto2));
        Assert.assertNull(collateralService.saveCollateral(carDto3));
        Assert.assertNull(collateralService.saveCollateral(carDto4));
        Assert.assertNull(collateralService.saveCollateral(carDto5));
        Assert.assertNull(collateralService.saveCollateral(carDto6));

        Assert.assertNull(collateralService.saveCollateral(airplaneDto1));
        Assert.assertNull(collateralService.saveCollateral(airplaneDto2));
        Assert.assertNull(collateralService.saveCollateral(airplaneDto3));
        Assert.assertNull(collateralService.saveCollateral(airplaneDto4));

    }


    @Test
    public void test7_success_Get_Info_car_CollateralTest() {
        // Получение информации об объекте обеспечения(машина)

        // Создаем объекты обеспечения
        CarDto carDto1 = new CarDto(1l, "Mazda", "333", 2000.00, (short) 2017,
                BigDecimal.valueOf(9000000.33), LocalDate.of(2031, 07, 15));
        CarDto carDto2 = new CarDto(2l, "BMV", "X1", 1000.00, (short) 2017,
                BigDecimal.valueOf(7000000.33), LocalDate.of(2031, 07, 15));
        // Сохраняем объекты обеспечения
        collateralService.saveCollateral(carDto1);
        collateralService.saveCollateral(carDto2);

        // Сохраненный объект в БД
        CarDto carDtoFromDB = new CarDto(2l, "BMV", "X1", 1000.00, (short) 2017,
                BigDecimal.valueOf(7000000.33), LocalDate.of(2031, 07, 15));

        // Запрос сохраненного объекта обеспечения по id
        CarDto requestCar = new CarDto(2l, null, null, null, null,
                null, null);

        Assert.assertEquals(carDtoFromDB, collateralService.getInfo(requestCar));
    }

    @Test
    public void test8_success_Get_Info_car_with_Last_date_CollateralTest() {
        // Получение информации об объекте обеспечения(машина) по самой последней по дате стоимостной оценки

        // Создаем объекты обеспечения
        CarDto carDto = new CarDto(1l, "Mazda", "333", 2000.00, (short) 2017,
                BigDecimal.valueOf(9000000.33), LocalDate.of(2016, 07, 15));

        // Сохраняем объекты обеспечения
        collateralService.saveCollateral(carDto);
        carDto.setId(1l);
        // Добавление второй стоимостной оценки(самая поздняя дата)
        carDto.setDate(LocalDate.of(2034, 07, 15));
        carDto.setValue(BigDecimal.valueOf(11111111.44));
        collateralService.saveCollateral(carDto);
        // Добавление третьей стоимостной оценки
        carDto.setDate(LocalDate.of(2014, 02, 11));
        carDto.setValue(BigDecimal.valueOf(700000));
        collateralService.saveCollateral(carDto);

        // Сохраненный объект carDto в БД с самой последней по дате стоимостной оценкой
        CarDto carDtoFromDB = new CarDto(1l, "Mazda", "333", 2000.00, (short) 2017,
                BigDecimal.valueOf(11111111.44), LocalDate.of(2034, 07, 15));

        // Запрос сохраненного объекта обеспечения по id
        CarDto requestCar = new CarDto(1l, null, null, null, null,
                null, null);

        Assert.assertEquals(carDtoFromDB, collateralService.getInfo(requestCar));
    }


    @Test
    public void test9_failed_getInfo_Car_with_null_id() {
        // Получение информации об объекте обеспечения с отсутствующим id
        CarDto carDto = new CarDto(null, "xf", "X1", 1000.00, (short) 2017,
                BigDecimal.valueOf(100.00), LocalDate.of(2020, 07, 15));
        Assert.assertNull(collateralService.getInfo(carDto));

    }


}
