package com.pk.auth.service;

import com.pk.auth.model.AuthRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {

    public void createUser(String userName, String password) {
    }


    public String login(AuthRequest request) {

        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", "spring-client");
        params.add("grant_type", "password");
        params.add("username", request.getUserName());
        params.add("password", request.getPassword());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> entity =
                new HttpEntity<>(params, headers);

        ResponseEntity<String> response =
                restTemplate.postForEntity(
                        "http://localhost:8180/realms/demo-realm/protocol/openid-connect/token",
                        entity,
                        String.class
                );

        return response.getBody();
    }

    public void assignRole(String userName, String user) {

    }


}
