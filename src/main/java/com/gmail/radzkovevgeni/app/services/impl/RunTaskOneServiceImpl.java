package com.gmail.radzkovevgeni.app.services.impl;

import com.gmail.radzkovevgeni.app.repository.utilites.RandomUtil;
import com.gmail.radzkovevgeni.app.services.RunTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunTaskOneServiceImpl implements RunTaskService {
    private static final Integer MAX_VALUE = 15;
    private static final int NUMBER_OF_RANDOM_NUMBERS = 3;

    private static final RandomUtil randomUtil = new RandomUtil();
    private static final Logger logger = LoggerFactory.getLogger(RunTaskOneServiceImpl.class);

    private static RunTaskOneServiceImpl instance;

    private RunTaskOneServiceImpl() {

    }

    @Override
    public void runTask() {
        Integer x = randomUtil.getRandom(MAX_VALUE);
        Integer y = randomUtil.getRandom(MAX_VALUE);
        Integer z = randomUtil.getRandom(MAX_VALUE);

        if (x > z) {
            logger.debug("DEBUG TYPE: sum x+y = " + String.valueOf(x + y));
        } else {
            logger.debug("DEBUG TYPE: z = " + String.valueOf(z));
        }

        logger.info("INFO TYPE: The arithmetic mean x,y,z - " + String.valueOf((x + y + z) / NUMBER_OF_RANDOM_NUMBERS));
    }

    public static RunTaskOneServiceImpl getInstance() {
        if (instance == null) {
            instance = new RunTaskOneServiceImpl();
        }
        return instance;
    }
}
