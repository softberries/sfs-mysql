package com.softwarepassion.sfs.repository.service;

import com.softwarepassion.sfs.forms.RegistrationForm;
import com.softwarepassion.sfs.model.Role;
import com.softwarepassion.sfs.model.User;
import com.softwarepassion.sfs.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> searchByMultipleColumns(String searchTerm, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("password", "secret", "enabled", "roles")
                .withMatcher("searchString", ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.CONTAINING).ignoreCase());
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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Optional<User> userOpt = userRepository.findByEmail(email);
        if (!userOpt.isPresent()) {
            throw new UsernameNotFoundException("No user found with username: " + email);
        }
        User user = userOpt.get();
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), user.isEnabled(), true, true,
                true, getAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }
}
