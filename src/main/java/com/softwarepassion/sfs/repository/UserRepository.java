package com.softwarepassion.sfs.repository;

import com.softwarepassion.sfs.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Long>, QueryByExampleExecutor<User> {

    Page<User> findAll(Pageable pageable);

    Optional<User> findByEmail(String email);

}