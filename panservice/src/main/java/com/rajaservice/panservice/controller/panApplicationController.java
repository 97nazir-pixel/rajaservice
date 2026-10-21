package com.rajaservice.panservice.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajaservice.panservice.dto.PanApplicationRequest;
import com.rajaservice.panservice.dto.PanApplicationResponse;
import com.rajaservice.panservice.service.PanApplicationService;


@RestController
@RequestMapping("/api/panApplication")
public class panApplicationController {

    private final PanApplicationService panApplicationService;

    public panApplicationController(PanApplicationService panApplicationService) {
        this.panApplicationService = panApplicationService;
    }
    @PostMapping(
        value = "/create",
        consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createApplication(@RequestBody PanApplicationRequest request) {
        PanApplicationResponse response = panApplicationService.createApplication(request);

        //     Map<String, String> responses = new HashMap<>();
    // responses.put("message", "Application received successfully");

    // return ResponseEntity.ok(responses);
        return "Application created successfully "+response.getApplicationId()+" at "+response.getCreatedAt()+" with status "+response.getStatus();
    }
    

}
