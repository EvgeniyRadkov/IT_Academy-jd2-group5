package com.gmail.radzkovevgeni.repository.impl;

import com.gmail.radzkovevgeni.repository.UniqueNumberRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class UniqueNumberRepositoryImpl implements UniqueNumberRepository {
    @Override
    public Integer getUniqueNumber() {
        UUID uuid = UUID.randomUUID();
        String stringUuid = uuid.toString();
        String replace = stringUuid.replace("-", "");
        int uniqueNumber = replace.hashCode();
        uniqueNumber = uniqueNumber < 0 ? -uniqueNumber : uniqueNumber;
        return uniqueNumber;
    }
}
