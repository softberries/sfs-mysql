package com.softwarepassion.sfs.util;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class GravatarService {

    private static final String GRAVATAR_BASE_URL = "https://www.gravatar.com/avatar/";

    @Cacheable("gravatarURLs")
    public String getGravatarURL(String email) {
        System.out.println("generation hash for: " + email);
        String hash = md5Hex(email);
        return GRAVATAR_BASE_URL + hash;
    }

    private static String md5Hex(String message) {
        try {
            MessageDigest md =
                MessageDigest.getInstance("MD5");
            return hex(md.digest(message.getBytes("CP1252")));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {

        }
        return null;
    }

    private static String hex(byte[] array) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(Integer.toHexString((array[i]
                & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }


}