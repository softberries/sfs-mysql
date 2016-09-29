package com.softwarepassion.sfs.web.dto;


import com.softwarepassion.sfs.model.Role;
import com.softwarepassion.sfs.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class UserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String fullName;
    private String dateCreated;
    private boolean active;
    private List<RoleDTO> roles;

    public static UserDTO fromUser(User user, List<Role> allRoles) {
        return new UserDTO(user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getFullName(), dateStr(null), user.isEnabled(), getRoleDTOs(allRoles, user.getRoles()));
    }

    private static List<RoleDTO> getRoleDTOs(Collection<Role> allRoles, Collection<Role> userRoles) {
        return allRoles.stream().map(r -> new RoleDTO(r.getName(), userRoles.contains(r))).collect(Collectors.toList());
    }

    private static String dateStr(Object o) {
        return null;
    }
}
