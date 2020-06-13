package com.facom.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {
    final String CURRENT_VERSION = "0.0.1";

    @GetMapping("/version")
    public String version(){
        return CURRENT_VERSION;
    }
}
