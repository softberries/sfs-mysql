package com.softwarepassion.sfs.repository.service;

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
public class UserRepositoryService {

    private final UserRepository userRepository;

    @Autowired
    public UserRepositoryService(UserRepository userRepository) {
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
}
