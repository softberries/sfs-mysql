package com.softwarepassion.sfs.repository;

import com.softwarepassion.sfs.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}