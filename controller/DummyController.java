package com.hotelmansys.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/country")
public class DummyController {
    @PostMapping("/addCountry")
    public String addCountry(){

        return "country added";
    }
}
