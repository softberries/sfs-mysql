package com.softwarepassion.sfs.repository.service;

import com.softwarepassion.sfs.forms.RegistrationForm;
import com.softwarepassion.sfs.model.Role;
import com.softwarepassion.sfs.model.User;
import com.softwarepassion.sfs.repository.RoleRepository;
import com.softwarepassion.sfs.repository.UserRepository;
import com.softwarepassion.sfs.repository.service.exception.UserAlreadyExistsException;
import com.softwarepassion.sfs.util.GravatarService;
import com.softwarepassion.sfs.web.dto.LoggedInUser;
import com.softwarepassion.sfs.web.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final GravatarService gravatarService;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder, GravatarService gravatarService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.gravatarService = gravatarService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Page<User> searchByMultipleColumns(String searchTerm, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("password", "enabled", "roles", "created")
                .withMatcher("searchString", ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.CONTAINING).ignoreCase());
        Example<User> example = Example.of(new User(searchTerm), matcher);
        return userRepository.findAll(example, pageable);
    }

    public long count() {
        return userRepository.count();
    }

    public boolean isEmailDuplicated(String email) {
        log.debug("Email duplication check for {}", email);
        return userRepository.findByEmail(email).isPresent();
    }

    public boolean validateEmailForUpdate(Long id, String email) {
        User user = userRepository.findOne(id);
        return user.getEmail().trim().equalsIgnoreCase(email) || !isEmailDuplicated(email);
    }

    public void register(RegistrationForm registrationForm) {
        log.debug("Registration for: {}", registrationForm);
        if (emailExist(registrationForm.getEmail())) {
            throw new UserAlreadyExistsException("There is an account already with that email address: " + registrationForm.getEmail());
        }
        final User user = new User();

        user.setFirstName(registrationForm.getFirstName());
        user.setLastName(registrationForm.getLastName());
        user.setPassword(passwordEncoder.encode(registrationForm.getPassword()));
        user.setEmail(registrationForm.getEmail());
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER").get()));
        user.setProfileImageUrl(gravatarService.getGravatarURL(registrationForm.getEmail(), Optional.empty()));
        user.setEnabled(false);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Optional<User> userOpt = userRepository.findByEmail(email);
        if (!userOpt.isPresent()) {
            throw new UsernameNotFoundException("No user found with username: " + email);
        }
        User user = userOpt.get();
        return new LoggedInUser(user.getEmail(), user.getPassword(), user.isEnabled(), true, true,
                true, getAuthorities(user.getRoles()), user.getFullName(), user.getProfileImageUrl(), user.getCreated().toString());
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }

    private boolean emailExist(final String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public void updateUser(UserDTO userDTO) {
        User user = userRepository.findOne(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setProfileImageUrl(gravatarService.getGravatarURL(userDTO.getEmail(), Optional.empty()));
        userRepository.save(user);
    }
}
