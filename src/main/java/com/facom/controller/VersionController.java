package com.facom.controller;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {
    final String CURRENT_VERSION = "0.0.1.0";

    @GetMapping("/version")
    public ResponseEntity<String> version() {
        JSONObject json = new JSONObject();
        json.put("version", CURRENT_VERSION);
        return ResponseEntity.ok(json.toString());
    }
}
