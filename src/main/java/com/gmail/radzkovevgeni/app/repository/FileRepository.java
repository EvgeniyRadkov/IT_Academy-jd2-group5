package com.gmail.radzkovevgeni.app.repository;

import com.gmail.radzkovevgeni.app.model.Car;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface FileRepository {
    File createFile(String nameFile);

    File writeStringToFile(File file, Set<Map.Entry<Integer, List<Car>>> text, Integer randomValue);

}

