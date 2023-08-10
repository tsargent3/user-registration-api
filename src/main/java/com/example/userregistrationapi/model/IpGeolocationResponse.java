package com.example.userregistrationapi.model;

public class IpGeolocationResponse {
    private String countryCode;
    private String city;

    public String getCountryCode() {
        return countryCode;
    }

    public String getCity() {
        return this.city;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String toString(){
        return this.countryCode + " " + this.city;
    }
}
