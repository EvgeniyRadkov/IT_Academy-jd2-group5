package com.gmail.radzkovevgeni.repository.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Document {
    private Integer id;
    private Integer uniqueNumber;
    private String description;
}
