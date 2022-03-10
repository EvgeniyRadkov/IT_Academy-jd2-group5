package com.gmail.radzkovevgeni.app.bsbjd2group5.runners;

import com.gmail.radzkovevgeni.app.bsbjd2group5.config.GenerationConfig;
import com.gmail.radzkovevgeni.app.bsbjd2group5.service.CalculateSumService;
import com.gmail.radzkovevgeni.app.bsbjd2group5.service.SumAllStringsService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class TaskRunner implements ApplicationRunner {

    private final CalculateSumService calculateSumService;
    private final SumAllStringsService sumAllStringsService;
    private final GenerationConfig generationConfig;

    @Override
    public void run(ApplicationArguments args) {
        String path = generationConfig.getPath();
        String loggFile = generationConfig.getLoggFile();
        int sumCalculation = sumAllStringsService.sumCalculation(path);
        log.info(String.valueOf(sumCalculation), loggFile);
    }
}
