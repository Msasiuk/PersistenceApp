package com.persistenceapp.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig { // Clase de configuración que carga los valores de configuración JWT

    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.issuer}")
    private String issuer;

    @Value("${security.jwt.ttlMillis}")
    private long ttlMillis;

    public String getSecret() {
        return secret;
    }

    public String getIssuer() {
        return issuer;
    }

    public long getTtlMillis() {
        return ttlMillis;
    }
}
