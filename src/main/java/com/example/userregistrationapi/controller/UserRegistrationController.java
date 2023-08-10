package com.example.userregistrationapi.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import com.example.userregistrationapi.model.UserRegistrationRequest;
import com.example.userregistrationapi.model.UserRegistrationResponse;
import com.example.userregistrationapi.service.IpGeolocationService;

@RestController
@RequestMapping("/api")
@Validated
public class UserRegistrationController {

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationRequest request, BindingResult bindingResult) {
        // Check for validation errors
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            bindingResult.getFieldErrors().forEach(error -> errors.add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        // Validate IP location
        IpGeolocationService geoService = new IpGeolocationService();
        if (!geoService.isCanadianIp(request.getIpAddress())) {
            return ResponseEntity.badRequest().body("You are not eligible to register from outside of Canada.");
        }

        // Generate random UUID
        String uuid = UUID.randomUUID().toString();

        // Get city name from IP geolocation service
        String cityName = geoService.getCityNameFromIp(request.getIpAddress());

        // Construct welcome message
        String welcomeMessage = "Welcome, " + request.getUsername() + "! You have successfully registered from " + cityName + ".";

        // Return 200 with UUID and welcome message
        UserRegistrationResponse response = new UserRegistrationResponse(uuid, welcomeMessage);
        return ResponseEntity.ok(response);
    }
}
