package com.gmail.radzkovevgeni.validation.impl;

import com.gmail.radzkovevgeni.config.ValidateConfig;
import com.gmail.radzkovevgeni.service.model.DocumentDTO;
import com.gmail.radzkovevgeni.validation.DocumentValidationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DocumentValidationServiceImpl implements DocumentValidationService {
    private final ValidateConfig validateConfig;

    @Override
    public DocumentDTO getValidDocumentDTO(DocumentDTO documentDTO) {
        String description = documentDTO.getDescription();
        if (description.length() > validateConfig.getMaxLengthDescription()) {
            throw new IllegalArgumentException("Description length is longer than default!");
        } else {
            return documentDTO;
        }
    }
}
