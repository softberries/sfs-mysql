package com.softwarepassion.sfs.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class RoleDTO implements Serializable {
    private String name;
    private boolean enabled;

    public RoleDTO(String name, boolean enabled) {
        this.name = name;
        this.enabled = enabled;
    }

    public RoleDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleDTO roleDTO = (RoleDTO) o;

        if (isEnabled() != roleDTO.isEnabled()) return false;
        return getName() != null ? getName().equals(roleDTO.getName()) : roleDTO.getName() == null;

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (isEnabled() ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RoleDTO{" +
                "name='" + name + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
