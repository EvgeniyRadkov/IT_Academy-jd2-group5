package com.gmail.radzkovevgeni.app.services.impl;

import com.gmail.radzkovevgeni.app.repository.impl.ArrayRepositoryImpl;
import com.gmail.radzkovevgeni.app.services.RunTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

public class RunTaskTwoServiceImpl implements RunTaskService {
    private static final int SIZE_ARRAY = 10;
    private static final int NUMBER_FROM = -300;
    private static final int NUMBER_TO = 300;

    private static final Logger logger = LoggerFactory.getLogger(RunTaskTwoServiceImpl.class);

    private static RunTaskTwoServiceImpl instance;

    private RunTaskTwoServiceImpl() {
    }

    @Override
    public void runTask() {
        ArrayRepositoryImpl arrayRepository = ArrayRepositoryImpl.getInstance();
        List<Integer> integerArray = arrayRepository.getIntegerArray(SIZE_ARRAY, NUMBER_FROM, NUMBER_TO);

        Collections.sort(integerArray);
        Integer min = integerArray.get(0);
        Integer max = integerArray.get(integerArray.size() - 1);

        Integer replaceableValue = min * max;
        integerArray.set(integerArray.size() - 1, replaceableValue);

        for (int i = 0; i < integerArray.size(); i++) {
            Integer value = integerArray.get(i);
            logger.error(String.valueOf(value));
        }
    }

    public static RunTaskTwoServiceImpl getInstance() {
        if (instance == null) {
            instance = new RunTaskTwoServiceImpl();
        }
        return instance;
    }
}
