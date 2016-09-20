package com.softwarepassion.sfs.repository.service;

import com.softwarepassion.sfs.forms.RegistrationForm;
import com.softwarepassion.sfs.model.User;
import com.softwarepassion.sfs.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> searchByMultipleColumns(String searchTerm, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching().
                withMatcher("searchString", ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.CONTAINING).ignoreCase());
        Example<User> example = Example.of(new User(searchTerm), matcher);
        return userRepository.findAll(example, pageable);
    }

    public long count() {
        return userRepository.count();
    }

    public boolean isEmailDuplicated(String email) {
        log.debug("Email duplication check for {}", email);
        //todo: implement email duplication check
        return false;
    }

    public void register(RegistrationForm registrationForm) {
        log.debug("Registration for: {}", registrationForm);
        //todo: implement register new user method
    }
}
