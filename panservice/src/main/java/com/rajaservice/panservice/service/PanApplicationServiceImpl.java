package com.rajaservice.panservice.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.rajaservice.panservice.Mapper.PanServiceMapper;
import com.rajaservice.panservice.dto.ApplicationStatus;
import com.rajaservice.panservice.dto.PanApplicationRequest;
import com.rajaservice.panservice.dto.PanApplicationResponse;
import com.rajaservice.panservice.entity.PanApplication;
import com.rajaservice.panservice.repository.PanServiceRepository;

@Service
public class PanApplicationServiceImpl implements PanApplicationService {
    
    private final PanServiceRepository panServiceRepository;
    private final PanServiceMapper panServiceMapper;

    public PanApplicationServiceImpl(PanServiceRepository panServiceRepository, PanServiceMapper panServiceMapper) {
        this.panServiceRepository = panServiceRepository;
        this.panServiceMapper = panServiceMapper;
    }

    @Override
    public PanApplicationResponse createApplication(PanApplicationRequest request) {
        LocalDateTime createdAt = LocalDateTime.now();

        PanApplication application = PanApplication.builder()
            .applicationNumber(UUID.randomUUID().toString().replace("-", "").substring(0, 30))
            .fullName(request.getFullName())
            .applicationType(request.getApplicationType())
            .email(request.getEmail())
            .status(ApplicationStatus.CREATED)
            .createdAt(createdAt)
            .mobileNumber(request.getMobileNumber())
            // .documentNames(request.getDocumentNames())
            .build();

        PanApplication savedApplication = panServiceRepository.save(application);

        return panServiceMapper.toResponse(savedApplication);
    }
}
