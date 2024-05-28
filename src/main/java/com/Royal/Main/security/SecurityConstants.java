package com.Royal.Main.security;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

public class SecurityConstants {
    private static final String SecretKeyString = "omwWpqmoweds31!qweJNNASD02130ehqweqwe@!!23qwoemp";
    static final SecretKey secretKey = Keys.hmacShaKeyFor(SecretKeyString.getBytes(StandardCharsets.UTF_8));
}
