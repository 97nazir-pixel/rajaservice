package com.rajaservice.panservice.Mapper;

import org.springframework.stereotype.Component;

import com.rajaservice.panservice.dto.ApplicationStatus;
import com.rajaservice.panservice.dto.PanApplicationRequest;
import com.rajaservice.panservice.dto.PanApplicationResponse;
import com.rajaservice.panservice.entity.PanApplication;

@Component
public class PanServiceMapper {
    
// Request DTO -> Entity
    public PanApplication toEntity(PanApplicationRequest request) {

        return PanApplication.builder()
                .fullName(request.getFullName())
                .applicationType(request.getApplicationType())
                .email(request.getEmail())
                .status(ApplicationStatus.CREATED)
                .build();
    }

    // Entity -> Response DTO
    public PanApplicationResponse toResponse(PanApplication application) {

        return PanApplicationResponse.builder()
                .applicationId(application.getId() == null ? null : application.getId().toString())
                .status(application.getStatus() == null ? null : application.getStatus().name())
                .createdAt(application.getCreatedAt() == null
                        ? null
                        : java.util.Date.from(application.getCreatedAt()
                                .atZone(java.time.ZoneId.systemDefault())
                                .toInstant()))
                .build();
    }
}
