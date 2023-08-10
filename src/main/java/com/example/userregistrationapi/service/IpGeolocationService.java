package com.example.userregistrationapi.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.userregistrationapi.model.IpGeolocationResponse;

@Service
public class IpGeolocationService {

    private static final String GEOLOCATION_API_URL = "http://ip-api.com/json/";

    public boolean isCanadianIp(String ipAddress) {
        String apiUrl = GEOLOCATION_API_URL + ipAddress;

        RestTemplate restTemplate = new RestTemplate();
        IpGeolocationResponse response = restTemplate.getForObject(apiUrl, IpGeolocationResponse.class);
        
        // Check if the IP is in Canada
        return response != null && "CA".equalsIgnoreCase(response.getCountryCode());
    }

    public String getCityNameFromIp(String ipAddress){
        String apiUrl = GEOLOCATION_API_URL + ipAddress;

        RestTemplate restTemplate = new RestTemplate();
        IpGeolocationResponse response = restTemplate.getForObject(apiUrl, IpGeolocationResponse.class);

        if(response != null)
            return response.getCity();
        else
            return "";
    }
}