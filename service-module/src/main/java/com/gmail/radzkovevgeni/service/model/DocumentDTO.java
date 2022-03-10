package com.gmail.radzkovevgeni.service.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class DocumentDTO {
    private Integer id;
    private Integer uniqueNumber;
    private String description;
}
