package com.rajaservice.panservice.dto;


import java.util.Date;

public class PanApplicationResponse {

    private String applicationId;
    private String status;
    private Date createdAt;

    public PanApplicationResponse() {
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String applicationId;
        private String status;
        private Date createdAt;

        private Builder() {
        }

        public Builder applicationId(String applicationId) {
            this.applicationId = applicationId;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder createdAt(Date createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public PanApplicationResponse build() {
            PanApplicationResponse response = new PanApplicationResponse();
            response.applicationId = applicationId;
            response.status = status;
            response.createdAt = createdAt;
            return response;
        }
    }
}
