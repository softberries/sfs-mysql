package com.softwarepassion.sfs.repository.service;

import com.softwarepassion.sfs.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    private static final int TOTAL_ELEMENTS = 1000;

    @Autowired
    private UserService userService;

    @Test
    @WithMockUser(username = "ram", roles = {"ADMIN"})
    public void shouldFindAllRecords() {
        //given
        String searchTerm = null;
        Pageable pageable = null;
        //when
        Page<User> users = userService.searchByMultipleColumns(searchTerm, pageable);
        //then
        assertTrue(users.getNumberOfElements() == TOTAL_ELEMENTS);
        assertTrue(users.getTotalElements() == TOTAL_ELEMENTS);
        assertTrue(users.getTotalPages() == 1);
    }

    @Test
    @WithMockUser(username = "ram", roles = {"ADMIN"})
    public void shouldFindAllRecordsDividedByPage() {
        //given
        String searchTerm = null;
        Pageable pageable = new PageRequest(0, 100);
        //when
        Page<User> users = userService.searchByMultipleColumns(searchTerm, pageable);
        //then
        assertTrue(users.getNumberOfElements() == 100);
        assertTrue(users.getTotalElements() == TOTAL_ELEMENTS);
        assertTrue(users.getTotalPages() == 10);
    }

    @Test
    @WithMockUser(username = "ram", roles = {"ADMIN"})
    public void shouldFindSingleRecordByEmail() {
        //given
        String searchTerm = "kgrajek@gmail.com";
        Pageable pageable = new PageRequest(0, 100);
        //when
        Page<User> users = userService.searchByMultipleColumns(searchTerm, pageable);
        //then
        assertTrue(users.getNumberOfElements() == 1);
        assertTrue(users.getTotalElements() == 1);
        assertTrue(users.getTotalPages() == 1);
    }

    @Test
    @WithMockUser(username = "ram", roles = {"ADMIN"})
    public void shouldFindEmailDuplication() {
        //given
        String email = "kgrajek@gmail.com";
        //when
        boolean result = userService.isEmailDuplicated(email);
        //then
        assertTrue(result);
    }
}