package com.template.spring.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class Config {

    public static final String DATE_FORMAT = "MM/dd/yyyy";
    public static final String DATE_TEXT_FORMAT = "MMMM dd, yyyy";
    public static final String DATE_TIME_FULL = "dd 'de' MMMM 'de' yyyy 'as' HH:mm";
    public static final String DATE_FORMAT_ISO = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String DATE_FORMAT_TIME_TABLE = "MM/dd/yyyy HH:mm a";
    public static final String DATE_FORMAT_GSON = "MMMM dd, yyyy, HH:mm:ss a";
    public static final Integer PAGE_SIZE = 10;


    // SCHEMAS
    public static final String AUTH_SCHEMA = "auth";

    public static String secret;
    public static String salt;


    // OAUTH SECRETS
    public static String googleSecret;
    public static String googleId;

    @Autowired
    public Config(Environment env) {
        secret = env.getProperty("security.client-secret");
        salt = env.getProperty("security.salt");
        googleSecret = env.getProperty("spring.security.oauth2.client.registration.google.client-secret");
        googleId = env.getProperty("spring.security.oauth2.client.registration.google.client-id");
    }
}
