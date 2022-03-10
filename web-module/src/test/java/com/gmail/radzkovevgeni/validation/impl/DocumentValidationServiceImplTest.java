package com.gmail.radzkovevgeni.validation.impl;

import com.gmail.radzkovevgeni.config.ValidateConfig;
import com.gmail.radzkovevgeni.repository.UniqueNumberRepository;
import com.gmail.radzkovevgeni.service.model.DocumentDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DocumentValidationServiceImplTest {
    @InjectMocks
    private DocumentValidationServiceImpl documentValidationService;

    @Mock
    private ValidateConfig validateConfig;

    @Mock
    private UniqueNumberRepository uniqueNumberRepository;

    @Test
    void shouldReturnExceptionWhenLineWithDescriptionIsBlank() {
        when(validateConfig.getMaxLengthDescription()).thenReturn(100);
        DocumentDTO documentDTO = DocumentDTO.builder()
                .uniqueNumber(uniqueNumberRepository.getUniqueNumber())
                .description("    ")
                .build();
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> documentValidationService.getValidDocumentDTO(documentDTO)
        );
        Assertions.assertEquals("Description cannot be empty. Enter the document description", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionWhenLineWithDescriptionLongerThanMaxLength() {
        when(validateConfig.getMaxLengthDescription()).thenReturn(100);
        DocumentDTO documentDTO = DocumentDTO.builder()
                .uniqueNumber(uniqueNumberRepository.getUniqueNumber())
                .description("kjguihjfnjxhcvxuhd u dvdovidkjdbvdhvuiah goagadjua f2123r2 sdfshdjhdhdhv" +
                        "ssbefbshfisdjhf 24324h43hr 3h5f hiu4 h6dw dtd3h4o5i35b3 h5ou53434uh4ihtjht24b" +
                        "34htiu4whti uhtiuh 4iuth piu4htpi uw4hti ujhwerkjhrkg j w;")
                .build();
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> documentValidationService.getValidDocumentDTO(documentDTO)
        );
        Assertions.assertEquals("Description is too long", exception.getMessage());
    }
}