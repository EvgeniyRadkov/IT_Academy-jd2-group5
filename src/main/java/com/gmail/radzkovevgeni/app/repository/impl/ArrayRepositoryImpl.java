package com.gmail.radzkovevgeni.app.repository.impl;

import com.gmail.radzkovevgeni.app.model.Car;
import com.gmail.radzkovevgeni.app.model.CarModelEnum;
import com.gmail.radzkovevgeni.app.repository.ArrayRepository;
import com.gmail.radzkovevgeni.app.repository.utilites.RandomUtil;

import java.util.ArrayList;
import java.util.List;

public class ArrayRepositoryImpl implements ArrayRepository {
    private final RandomUtil randomUtil = new RandomUtil();

    private static ArrayRepositoryImpl instance;

    private ArrayRepositoryImpl() {

    }

    @Override
    public List<Integer> getIntegerArray(Integer sizeArray, Integer numberFrom, Integer numberTo) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < sizeArray; i++) {
            Integer value = randomUtil.getRangeOfNumbersRandom(numberFrom, numberTo);
            numbers.add(value);
        }
        return numbers;
    }

    @Override
    public List<Car> carList(Integer listSize, Integer numberFrom, Integer numberTo, String[] nameArray) {
        List<Car> cars = new ArrayList<>();
        CarModelEnum[] values = CarModelEnum.values();
        for (int i = 0; i < listSize; i++) {
            Car car = new Car();
            car.setName(randomUtil.getRandomName(nameArray));
            car.setCarModel(randomUtil.getRandomEnum(values));
            car.setEngineCapacity(randomUtil.getRangeOfNumbersRandom(numberFrom, numberTo));
            cars.add(car);
        }
        return cars;
    }

    public static ArrayRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new ArrayRepositoryImpl();
        }
        return instance;
    }
}
