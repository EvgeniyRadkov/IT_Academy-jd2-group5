package com.gmail.radzkovevgeni.app.repository.impl;

import com.gmail.radzkovevgeni.app.model.Car;
import com.gmail.radzkovevgeni.app.repository.FileRepository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FileRepositoryImpl implements FileRepository {
    private static FileRepositoryImpl instance;

    private FileRepositoryImpl() {

    }

    @Override
    public File createFile(String nameFile) {
        File file = new File(nameFile);
        if (!file.exists()) {
            try {
                boolean isNewFile = file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return file;
    }

    @Override
    public File writeStringToFile(File file, Set<Map.Entry<Integer, List<Car>>> text, Integer randomValue) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Map.Entry<Integer, List<Car>> entry : text) {
                if (entry.getKey() == randomValue) {
                    bw.write(entry.toString());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return file;
    }

    public static FileRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new FileRepositoryImpl();
        }
        return instance;
    }
}
