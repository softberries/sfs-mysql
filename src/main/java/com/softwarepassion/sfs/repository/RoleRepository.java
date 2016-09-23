package com.softwarepassion.sfs.repository;

import com.softwarepassion.sfs.model.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {

    Optional<Role> findByName(String name);

}
