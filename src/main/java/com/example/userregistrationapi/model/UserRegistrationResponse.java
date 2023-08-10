package com.example.userregistrationapi.model;

public class UserRegistrationResponse {
    private String uuid;
    private String welcomeMessage;

    public UserRegistrationResponse(String uuid, String welcomeMessage) {
        this.uuid = uuid;
        this.welcomeMessage = welcomeMessage;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }
}