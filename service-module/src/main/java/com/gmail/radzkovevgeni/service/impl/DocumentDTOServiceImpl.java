package com.gmail.radzkovevgeni.service.impl;

import com.gmail.radzkovevgeni.repository.model.Document;
import com.gmail.radzkovevgeni.service.model.DocumentDTO;
import com.gmail.radzkovevgeni.repository.DocumentRepository;
import com.gmail.radzkovevgeni.service.DocumentDTOService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Service
@Slf4j
@AllArgsConstructor
public class DocumentDTOServiceImpl implements DocumentDTOService {
    private final DocumentRepository documentRepository;
    private final DataSource dataSource;

    @Override
    public DocumentDTO add(DocumentDTO documentDTO) {
        Document document = convertDocumentDTOToDocument(documentDTO);
        try {
            Connection connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            try {
                Document addedDocument = documentRepository.add(connection, document);
                DocumentDTO addedDocumentDTO = convertToDocumentDTO(addedDocument);
                connection.commit();
                return addedDocumentDTO;
            } catch (SQLException e) {
                connection.rollback();
                log.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    private DocumentDTO convertToDocumentDTO(Document addedDocument) {
        return DocumentDTO.builder()
                .id(addedDocument.getId())
                .uniqueNumber(addedDocument.getUniqueNumber())
                .description(addedDocument.getDescription())
                .build();
    }

    private Document convertDocumentDTOToDocument(DocumentDTO documentDTO) {
        return Document.builder()
                .id(documentDTO.getId())
                .uniqueNumber(documentDTO.getUniqueNumber())
                .description(documentDTO.getDescription())
                .build();
    }
}
