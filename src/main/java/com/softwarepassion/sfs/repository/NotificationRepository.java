package com.softwarepassion.sfs.repository;

import com.softwarepassion.sfs.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    Optional<Notification> findByUuid(String uuid);
}
