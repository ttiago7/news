package com.news.news.controller;

import com.news.news.model.response.ApiDolarResponse;
import com.news.news.service.ApiCallService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RequestMapping("/api")
@RestController
public class ApiCallController {

    @Autowired
    ApiCallService apiCallService;

    @GetMapping
    @Operation(summary = "Endpoins API clima")
    public Object callAPI() {
        try {
            ApiDolarResponse apiDolarResponse = apiCallService.callAPI();
//            if (apiDolarResponse.getDolarBlue() == null){
//                return apiCallService.callAPI2();
//            }
            return apiDolarResponse;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
