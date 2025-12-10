package com.snhu.sslserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
@RestController
public class SslServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SslServerApplication.class, args);
    }

    @GetMapping("/hash")
    public String getChecksum() {
        // Replace with YOUR actual name
        String data = "Hello World Check Sum! - Kelly Reinersman";
        
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
            String hexHash = bytesToHex(hash);
            
            return "<h1>Checksum Verification</h1>" +
                   "<p><strong>Data:</strong> " + data + "</p>" +
                   "<p><strong>Algorithm:</strong> SHA-256</p>" +
                   "<p><strong>Checksum:</strong> " + hexHash + "</p>";
        } catch (NoSuchAlgorithmException e) {
            return "Error: " + e.getMessage();
        }
    }
    
    private String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}