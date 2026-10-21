package com.rajaservice.panservice.service;

import com.rajaservice.panservice.dto.PanApplicationRequest;
import com.rajaservice.panservice.dto.PanApplicationResponse;

public interface PanApplicationService {

    public PanApplicationResponse createApplication(PanApplicationRequest request);
}
