package com.college.hackathon.controller;

// Import DTO classes (Data Transfer Objects)
import com.college.hackathon.dto.*;

// Import service layer for business logic
import com.college.hackathon.service.AuthService;

// Used for validating request data automatically
import jakarta.validation.Valid;

// Used to control HTTP responses (status, headers, body)
import org.springframework.http.*;

// Used to define REST APIs
import org.springframework.web.bind.annotation.*;

// Marks this class as a REST Controller (handles HTTP requests)
@RestController

// Base URL mapping for all endpoints in this controller
@RequestMapping("/api/auth")
public class AuthController {

    // Service layer object to handle authentication logic
    private final AuthService authService;

    // Constructor Injection (Spring automatically injects AuthService)
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // -------------------- REGISTER API --------------------

    // Handles POST request at /api/auth/register
    @PostMapping("/register")

    // Method to register a new user
    public ResponseEntity<ApiResponse<AuthResponse>> register(

            // @Valid → validates request body based on annotations
            // @RequestBody → converts JSON input into Java object
            @Valid @RequestBody RegisterRequest request) {

        // Returns HTTP 201 (CREATED) with success message and response data
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok(
                        "Registered successfully",                // Message
                        authService.register(request)             // Calls service layer
                ));
    }

    // -------------------- LOGIN API --------------------

    // Handles POST request at /api/auth/login
    @PostMapping("/login")

    // Method to login user
    public ResponseEntity<ApiResponse<AuthResponse>> login(

            // Validates and maps JSON request to LoginRequest object
            @Valid @RequestBody LoginRequest request) {

        // Returns HTTP 200 (OK) with login success message and token/data
        return ResponseEntity.ok(
                ApiResponse.ok(
                        "Login successful",                      // Message
                        authService.login(request)               // Calls service logic
                )
        );
    }
}
