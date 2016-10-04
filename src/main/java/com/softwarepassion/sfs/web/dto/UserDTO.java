package com.softwarepassion.sfs.web.dto;


import com.softwarepassion.sfs.model.Role;
import com.softwarepassion.sfs.model.User;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDTO implements Serializable {

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    private String email;

    private String fullName;
    private String dateCreated;
    private boolean enabled;
    private List<RoleDTO> roles;

    public UserDTO(String firstName, String lastName, String email, String fullName, String dateCreated, boolean enabled, List<RoleDTO> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.fullName = fullName;
        this.dateCreated = dateCreated;
        this.enabled = enabled;
        this.roles = roles;
    }

    public UserDTO() {
    }

    public static UserDTO fromUser(User user) {
        return new UserDTO(user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getFullName(), dateStr(null), user.isEnabled(), getRoleDTOs(user.getRoles()));
    }

    public static List<RoleDTO> getRoleDTOs(Collection<Role> userRoles) {
        return userRoles.stream().map(r -> new RoleDTO(r.getId().toString(), r.getName())).collect(Collectors.toList());
    }

    private static String dateStr(Object o) {
        return null;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserDTO userDTO = (UserDTO) o;

        return isEnabled() == userDTO.isEnabled() && (getFirstName() != null
                ? getFirstName().equals(userDTO.getFirstName()) : userDTO.getFirstName() == null
                && (getLastName() != null ? getLastName().equals(userDTO.getLastName()) : userDTO.getLastName() == null
                && (getEmail() != null ? getEmail().equals(userDTO.getEmail()) : userDTO.getEmail() == null
                && (getFullName() != null ? getFullName().equals(userDTO.getFullName()) : userDTO.getFullName() == null
                && (getDateCreated() != null ? getDateCreated().equals(userDTO.getDateCreated()) : userDTO.getDateCreated() == null
                && (getRoles() != null ? getRoles().equals(userDTO.getRoles()) : userDTO.getRoles() == null))))));

    }

    @Override
    public int hashCode() {
        int result = getFirstName() != null ? getFirstName().hashCode() : 0;
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getFullName() != null ? getFullName().hashCode() : 0);
        result = 31 * result + (getDateCreated() != null ? getDateCreated().hashCode() : 0);
        result = 31 * result + (isEnabled() ? 1 : 0);
        result = 31 * result + (getRoles() != null ? getRoles().hashCode() : 0);
        return result;
    }
}
