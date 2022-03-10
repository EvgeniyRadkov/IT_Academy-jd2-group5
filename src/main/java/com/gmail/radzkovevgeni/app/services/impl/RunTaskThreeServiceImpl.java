package com.gmail.radzkovevgeni.app.services.impl;

import com.gmail.radzkovevgeni.app.model.Car;
import com.gmail.radzkovevgeni.app.repository.impl.ArrayRepositoryImpl;
import com.gmail.radzkovevgeni.app.repository.impl.FileRepositoryImpl;
import com.gmail.radzkovevgeni.app.repository.utilites.RandomUtil;
import com.gmail.radzkovevgeni.app.services.RunTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class RunTaskThreeServiceImpl implements RunTaskService {
    private static final String[] CAR_NAME = {"Audi", "BMW", "Lada", "Fiat", "Kia", "Hyundai", "Opel", "GMC", "ZAZ"};
    private static final Integer LIST_SIZE = 10;
    private static final Integer NUMBER_FROM = 1;
    private static final Integer NUMBER_TO = 3;
    private static final String NAME_FILE = "Task_Three.txt";

    private static final Logger logger = LoggerFactory.getLogger(RunTaskThreeServiceImpl.class);
    private static final RandomUtil randomUtil = new RandomUtil();

    private static RunTaskThreeServiceImpl instance;

    private RunTaskThreeServiceImpl() {

    }

    @Override
    public void runTask() {
        ArrayRepositoryImpl arrayRepository = ArrayRepositoryImpl.getInstance();
        FileRepositoryImpl fileRepository = FileRepositoryImpl.getInstance();

        List<Car> cars = arrayRepository.carList(LIST_SIZE, NUMBER_FROM, NUMBER_TO, CAR_NAME);

        Map<Integer, List<Car>> collect = cars.stream().collect(Collectors.groupingBy(Car::getEngineCapacity));

        Set<Map.Entry<Integer, List<Car>>> entries = collect.entrySet();

        File file = fileRepository.createFile(NAME_FILE);

        Integer randomValue = randomUtil.getRangeOfNumbersRandom(NUMBER_FROM, NUMBER_TO);

        fileRepository.writeStringToFile(file, entries, randomValue);

    }

    public static RunTaskThreeServiceImpl getInstance() {
        if (instance == null) {
            instance = new RunTaskThreeServiceImpl();
        }
        return instance;
    }
}
