package com.Test.SampleProject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final String SECRET_KEY = "MyChoice";

    private boolean authenticateUser(String username, String password) {
        // Replace this with your actual authentication logic
        return "user123".equals(username) && "password123".equals(password);
    }

    @GetMapping("/jwt")
    public ResponseEntity<Map<String, String>> login(@RequestParam String username, @RequestParam String password) {
        // Authenticate user
        if (!authenticateUser(username, password)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // Generate JWT token
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour expiration
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

        // Return token
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
//    @GetMapping("/protected-endpoint")
//    public ResponseEntity<String> protectedEndpoint(@RequestHeader("Authorization") String token) {
//        return new ResponseEntity<>("This is a protected endpoint", HttpStatus.OK);
//    }
}

