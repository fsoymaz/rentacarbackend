package com.tobeto.pair8.controllers;


import com.tobeto.pair8.services.abstracts.DistrictService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/test")
@AllArgsConstructor
@CrossOrigin
public class TestController {
    private final DistrictService districtService;



/*
    @GetMapping("refreshApi")
    public void refreshApi() {
        districtService.create();
    }
*/


}