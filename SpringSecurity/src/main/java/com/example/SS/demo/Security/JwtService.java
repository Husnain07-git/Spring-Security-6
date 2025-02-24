package com.example.SS.demo.Security;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.apache.tomcat.util.buf.UEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    // 256-bit Base64 encoded key (Ensure it's at least 32 bytes after decoding)
    private String SECRET_KEY = "aTKCJ+Tu/hX8az03QZw6RQRVYo0T4u+ILZsIPz7mfA6xZkzqNtjEeq5+iRbdvp1LAQrJOR3SxJFeaQn1mvWtxER5qR7gFUN1FeOG1W81hy3f2NVtG4qhxoqUyXVMh+1nnACGsUbWzChoDtfz0khRnVrqOI5oQwiMKl86GDuDspoMrV4mfXhUwGpFIy2rXPmh+FL0C5Y5e0sx7pp1gD3uzfTFxteZVYYsEU06MDCUaLtz1Tbp+IbhYvgkhbCB8kjexDC5SwLhomCR23xxshqmi6wZrV6x9lCGVN3dZbzgGiudHskRVqYsf517QxHbhc8//dds4RVDqozh5WnGkCZNcKAQXDzD+oc9hrv9sIhTJ16QIkCFUhidPiFyjsjXgYoRkZ6hMU2hUe6cdzLiSNTMxbZLZOVxEQOdqvGtQrOWVibkAMVggwmBK0UNnKyCDhYJHJIXCr2Z2hhyIGKaLrtd01YH3rLq+E02fBOCdynECFvBi4fJW6EYW7w3Oe7k0m8cdEueqLXvw9q5gF8bekJ/jFqlkHkPBUCQxLYVzkonAXUYODcxLWyynayypqG8zOiyGY0NLmoFHZtvuQb53nF7iMtYnHn6yeqqmT4+URkc7Buh7ac+3dfPIs7aU3oNrtXUVIseLp2KvoZH3dvIz+pgnqpZzLbMGCu1eDGIcO2mSOBSRq8zNjqwPP3feKsx/YvW\n";

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour expiration
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
