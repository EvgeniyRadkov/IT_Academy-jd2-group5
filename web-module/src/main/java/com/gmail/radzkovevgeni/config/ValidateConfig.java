package com.gmail.radzkovevgeni.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Getter
@PropertySource(value = "classpath:validate.properties")
public class ValidateConfig {
    @Value("100")
    private int maxLengthDescription;
}
