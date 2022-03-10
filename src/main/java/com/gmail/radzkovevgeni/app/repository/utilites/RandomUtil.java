package com.gmail.radzkovevgeni.app.repository.utilites;

import java.util.Random;

public class RandomUtil {
    private static final Random random = new Random();

    public Integer getRandom(Integer numberTo) {
        return random.nextInt(numberTo);
    }

    public Integer getRangeOfNumbersRandom(Integer numberFrom, Integer numbersTo) {
        return random.nextInt((numbersTo - numberFrom + 1) + numberFrom);
    }

    public String getRandomName(String[] nameArray) {
        int name = random.nextInt(nameArray.length);
        return nameArray[name];
    }

    public String getRandomEnum(Enum[] nameEnum) {
        return String.valueOf(nameEnum[random.nextInt(nameEnum.length)]);
    }
}
