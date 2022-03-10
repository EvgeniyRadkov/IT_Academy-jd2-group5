package com.gmail.radzkovevgeni.app.bsbjd2group5.repository.impl;

import com.gmail.radzkovevgeni.app.bsbjd2group5.repository.ReadFileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Repository
@Slf4j
public class ReadFileRepositoryImpl implements ReadFileRepository {

    @Override
    public List<String> stringAfterRead(String fileName) {
        List<String> stringLine = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            stringLine = bufferedReader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringLine;
    }
}