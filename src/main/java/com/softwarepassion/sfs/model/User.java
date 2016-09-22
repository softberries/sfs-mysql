package com.softwarepassion.sfs.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import org.thymeleaf.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@JsonIgnoreProperties({"password", "secret", "searchString", "roles"})
public class User implements Serializable {

    private static final String STRING_DELIMITER = " ";

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

    @Column(length = 60)
    private String password;

    private String secret;

    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    @Transient
    private String fullName;

    public User() {
        this.secret = UUID.randomUUID().toString();
        this.enabled = false;
    }

    public User(String searchString) {
        this.secret = UUID.randomUUID().toString();
        this.enabled = false;
        this.searchString = searchString;
    }

    public String getFullName() {
        return StringUtils.join(Arrays.asList(firstName, lastName), STRING_DELIMITER);
    }

    @PreUpdate
    @PrePersist
    void updateSearchString() {
        final String fullSearchString = StringUtils.join(Arrays.asList(
                id,
                firstName,
                lastName,
                email),
                STRING_DELIMITER);
        this.searchString = StringUtils.substring(fullSearchString, 0, 999);
    }

}
