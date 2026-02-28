package com.pk.auth.service;

import com.pk.auth.model.AuthRequest;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final Keycloak keycloak;

    private static final String REALM = "demo-realm";

    /**
     * Create a new user in Keycloak
     */
    public void createUser(String userName, String password) {
        // Check if user already exists
        List<UserRepresentation> existingUsers = keycloak.realm(REALM).users().search(userName);
        if (!existingUsers.isEmpty()) {
            throw new RuntimeException("User already exists: " + userName);
        }

        // Create user representation
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userName);
        user.setEnabled(true);

        // Create credentials
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(password);
        credential.setTemporary(false);

        user.setCredentials(Collections.singletonList(credential));

        // Create user in Keycloak
        Response response = keycloak.realm(REALM).users().create(user);
        if (response.getStatus() != 201) {
            throw new RuntimeException("Failed to create user: HTTP " + response.getStatus());
        }
        response.close();
    }

    /**
     * Assign role to a user
     */
    public void assignRole(String userName, String roleName) {
        // Find user by username
        List<UserRepresentation> users = keycloak.realm(REALM).users().search(userName);
        if (users.isEmpty()) {
            throw new RuntimeException("User not found: " + userName);
        }
        String userId = users.get(0).getId();

        // Get role representation
        RoleRepresentation role = keycloak.realm(REALM).roles().get(roleName).toRepresentation();

        // Assign realm role
        keycloak.realm(REALM).users().get(userId).roles().realmLevel().add(Collections.singletonList(role));
    }

    /**
     * Login using REST call (returns access token JSON)
     */
    public String login(AuthRequest request) {

        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", "spring-client");  // your client ID
        params.add("grant_type", "password");
        params.add("username", request.getUserName());
        params.add("password", request.getPassword());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(
                "http://localhost:8180/realms/demo-realm/protocol/openid-connect/token",
                entity,
                String.class);

        return response.getBody();
    }
}
