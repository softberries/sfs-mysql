package com.softwarepassion.sfs.repository;

import com.softwarepassion.sfs.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Long> {

    Page<User> findAll(Pageable pageable);

}