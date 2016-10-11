package com.softwarepassion.sfs.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;

import org.thymeleaf.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
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

    @Column
    private Date created;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Notification> notifications;

    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    @Transient
    private String fullName;

    public User() {
        this.enabled = false;
        this.fullName = StringUtils.join(Arrays.asList(firstName, lastName), STRING_DELIMITER);
    }

    public User(String searchString) {
        this.enabled = false;
        this.searchString = searchString;
        this.fullName = StringUtils.join(Arrays.asList(firstName, lastName), STRING_DELIMITER);
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
        this.searchString = fullSearchString.substring(0,
                fullSearchString.length() > 999 ? 999 : fullSearchString.length());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public String getFullName() {
        return StringUtils.join(Arrays.asList(firstName, lastName), STRING_DELIMITER);
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
