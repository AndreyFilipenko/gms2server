package com.facom.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class VersionController {
    @Value("${build.version}")
    private String CURRENT_VERSION;

    @GetMapping("/version")
    public ResponseEntity<Map<String, Object>> version() {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("version", CURRENT_VERSION);
        return ResponseEntity.ok(jsonMap);
    }
}
