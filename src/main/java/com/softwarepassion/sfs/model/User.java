package com.softwarepassion.sfs.model;


public class User {

    private String profileImageUrl;
    private String fullName;

    public User(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
        this.fullName = "Krzysztof Grajek";
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
