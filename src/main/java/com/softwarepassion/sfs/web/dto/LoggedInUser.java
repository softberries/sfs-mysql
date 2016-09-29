package com.softwarepassion.sfs.web.dto;


import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class LoggedInUser extends User {

    private final String fullName;
    private final String profileImageUrl;
    private final String createdDate;

    public LoggedInUser(String username, String password, boolean enabled,
                        boolean accountNonExpired, boolean credentialsNonExpired,
                        boolean accountNonLocked,
                        Collection authorities,
                        String fullName, String profileImageUrl, String createdDate) {

        super(username, password, enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, authorities);

        this.fullName = fullName;
        this.profileImageUrl = profileImageUrl;
        this.createdDate = createdDate;
    }

    public String getFullName() {
        return fullName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getCreatedDate() {
        return createdDate;
    }
}
