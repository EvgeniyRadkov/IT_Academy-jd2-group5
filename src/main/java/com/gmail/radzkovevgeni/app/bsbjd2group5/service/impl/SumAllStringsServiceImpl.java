package com.gmail.radzkovevgeni.app.bsbjd2group5.service.impl;

import com.gmail.radzkovevgeni.app.bsbjd2group5.repository.ReadFileRepository;
import com.gmail.radzkovevgeni.app.bsbjd2group5.service.CalculateSumService;
import com.gmail.radzkovevgeni.app.bsbjd2group5.service.SumAllStringsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class SumAllStringsServiceImpl implements SumAllStringsService {
    private final ReadFileRepository readFileRepository;
    private final CalculateSumService calculateSumService;

    @Override
    public int sumCalculation(String fileName) {
        int result = 0;
        List<String> strings = readFileRepository.stringAfterRead(fileName);
        for (String numbers : strings) {
            result += calculateSumService.add(numbers);
        }
        return result;
    }
}
