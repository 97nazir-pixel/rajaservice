package com.rajaservice.panservice.entity;

import java.time.LocalDateTime;

import com.rajaservice.panservice.dto.ApplicationStatus;
import com.rajaservice.panservice.dto.ApplicationType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "panservice_db")
@NoArgsConstructor
@Builder
@Data
public class PanApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "application_number", nullable = false,
            unique = true, length = 30)
    private String applicationNumber;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(nullable = false, length = 150)
    @Email 
    private String email;

    @Column(name = "mobile_number", nullable = false, length = 10)
    private String mobileNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "application_type", nullable = false, length = 20)
    private ApplicationType applicationType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private ApplicationStatus status;

    // @Lob
    // @Column(name = "document_data")
    // private byte[] documentData;

    // // Original uploaded filename(s)
    // @Column(name = "document_names", length = 2000)
    // private String documentNames;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public PanApplication(Long id, String applicationNumber, String fullName, String email,
                          String mobileNumber, ApplicationType applicationType,
                          ApplicationStatus status,  LocalDateTime createdAt) {
        this.id = id;
        this.applicationNumber = applicationNumber;
        this.fullName = fullName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.applicationType = applicationType;
        this.status = status;
        // this.documentData = documentData;
        // this.documentNames = documentNames;
        this.createdAt = createdAt;
    }
}
