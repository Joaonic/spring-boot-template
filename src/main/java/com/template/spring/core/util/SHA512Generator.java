package com.template.spring.core.util;

import org.apache.commons.codec.binary.Hex;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA512Generator implements PasswordEncoder {

    public static String generateValue(String param) {

        byte[] hash = null;

        try {

            hash = MessageDigest.getInstance("SHA-512").digest(param.getBytes());

        } catch (NoSuchAlgorithmException e) {
        }
        return Hex.encodeHexString(hash).toUpperCase();
    }

    @Override
    public String encode(CharSequence charSequence) {
        return generateValue(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        String senha = charSequence.toString();
        if (senha.length() == 128) { //se já for o hash sha512

            return senha.equals(s);
        }
        return generateValue(charSequence.toString()).equals(s);
    }
}
