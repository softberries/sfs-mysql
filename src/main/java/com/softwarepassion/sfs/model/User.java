package com.softwarepassion.sfs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.thymeleaf.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Arrays;

@Entity
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String profileImageUrl;

    @Column(length = 1000)
    private String searchString;

    @Transient
    private String fullName;

    public User(String searchString) {
        this.searchString = searchString;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @PreUpdate
    @PrePersist
    void updateSearchString() {
        final String fullSearchString = StringUtils.join(Arrays.asList(
            id,
            firstName,
            lastName,
            email),
            " ");
        this.searchString = StringUtils.substring(fullSearchString, 0, 999);
    }

}
