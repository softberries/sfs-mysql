package com.softwarepassion.sfs.util;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import static org.springframework.util.StringUtils.isEmpty;

@Service
@Slf4j
public class GravatarService {

    private static final String GRAVATAR_BASE_URL = "https://www.gravatar.com/avatar/";
    private static final int DEFAULT_IMG_SIZE = 32;

    @Cacheable("gravatarURLs")
    public String getGravatarURL(String email, Optional<Integer> size) {
        Preconditions.checkArgument(!isEmpty(email), "Gravatar url generation error, email cannot be null");
        Preconditions.checkArgument(!(size.isPresent() && size.get() <= 0), "Gravatar url generation error, size cannot be equal or less than 0");
        String hash = md5Hex(email);
        String sizePostfix = size.isPresent() ? "?s=" + size.get() : "?s=" + DEFAULT_IMG_SIZE;
        return GRAVATAR_BASE_URL + hash + sizePostfix;
    }

    private static String md5Hex(String message) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return hex(md.digest(message.getBytes("CP1252")));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ignored) {
            log.error("Error while generating md5Hex code", ignored);
        }
        return null;
    }

    private static String hex(byte[] array) {
        StringBuilder sb = new StringBuilder();
        for (byte anArray : array) {
            sb.append(Integer.toHexString((anArray
                    & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }


}