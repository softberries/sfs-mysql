package com.softwarepassion.sfs.web.dto;


import com.softwarepassion.sfs.model.Role;
import com.softwarepassion.sfs.model.User;
import org.assertj.core.util.Lists;
import org.hamcrest.core.Every;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.CoreMatchers.is;

public class UserDTOSpec {

    private List<Role> allRoles = Lists.newArrayList(new Role(1L, "ADMIN"), new Role(2L, "USER"));
    private User user = new User(1L, "Krzysztof", "Gra", "kgr@gmail.com", "http://blahblah.com", "--------", "pass", new Date(), true, Lists.emptyList(), "Krzysztof Gra");

    @Test
    public void shouldGenerateProperUserDTOWithAllRolesEnabled() {
        //given
        List<Role> userRoles = Lists.newArrayList(new Role(1L, "ADMIN"), new Role(2L, "USER"));
        user.setRoles(userRoles);
        //when
        UserDTO result = UserDTO.fromUser(user);
        //then
        assertThat(result.getRoles(), (Every.everyItem(hasProperty("enabled", is(true)))));
    }

    @Test
    public void shouldGenerateProperUserDTOWithAdminRoleOnly() {
        //given
        List<Role> userRoles = Lists.newArrayList(new Role(1L, "ADMIN"));
        user.setRoles(userRoles);
        //when
        UserDTO result = UserDTO.fromUser(user);
        //then
        assertThat(result.getRoles(), contains(
                hasProperty("enabled", is(true)),
                hasProperty("enabled", is(false))
        ));
    }
}