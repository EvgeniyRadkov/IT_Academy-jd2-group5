package com.gmail.radzkovevgeni.app.bsbjd2group5.repository;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface ReadFileRepository {

    List<String> stringAfterRead(String fileName);
}
