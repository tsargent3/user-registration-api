package com.example.userregistrationapi.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;

public class UserRegistrationRequest {
    @NotBlank(message = "Username cannot be blank")
    private String username;

    // Regex pattern to enforce requirements
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(
        regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[_,#,$,%]).{8,}$",
        message = "Password must contain at least one uppercase letter, one digit, and one special character (_,#,$,%)"
    )
    private String password;

    @NotBlank(message = "IP Address cannot be blank")
    private String ipAddress;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}