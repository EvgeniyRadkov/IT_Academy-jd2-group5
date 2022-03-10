package com.gmail.radzkovevgeni.app.bsbjd2group5.service.impl;

import com.gmail.radzkovevgeni.app.bsbjd2group5.service.CalculateSumService;
import com.gmail.radzkovevgeni.app.bsbjd2group5.service.exception.NumberCountException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class CalculateSumServiceImpl implements CalculateSumService {

    @Override
    public int add(String numbers) {
        List<String> stringListAfterSplit = List.of(numbers.split("[:,|]"));

        List<Integer> listIntegers = new ArrayList<>(stringListAfterSplit.size());
        for (int i = 0; i < stringListAfterSplit.size(); i++) {
            if (stringListAfterSplit.size() > 2) {
                try {
                    throw new NumberCountException("the number of elements in the string is greater than 2");
                } catch (NumberCountException e) {
                    log.error(e.getMessage());
                }
            } else {
                try {
                    listIntegers.add(Integer.valueOf(stringListAfterSplit.get(i)));
                } catch (NumberFormatException e) {
                    log.error("Parsing exception: there is not number in line " + i);
                }
            }
        }
        if (listIntegers.size() == 0) {
            return 0;
        } else {
            return listIntegers.stream()
                    .mapToInt(Integer::intValue)
                    .sum();
        }
    }
}
