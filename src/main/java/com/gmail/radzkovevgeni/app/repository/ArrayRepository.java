package com.gmail.radzkovevgeni.app.repository;

import com.gmail.radzkovevgeni.app.model.Car;

import java.util.List;

public interface ArrayRepository {
    List<Integer> getIntegerArray(Integer sizeArray, Integer numberFrom, Integer numberTo);

    List<Car> carList(Integer listSize, Integer numberFrom, Integer numberTo, String[] nameArray);
}
