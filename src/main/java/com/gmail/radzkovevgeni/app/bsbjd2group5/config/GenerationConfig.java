package com.gmail.radzkovevgeni.app.bsbjd2group5.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class GenerationConfig {

    @Value("${file.path}")
    private String path;

    @Value("${logging.file.name}")
    private String loggFile;
}
