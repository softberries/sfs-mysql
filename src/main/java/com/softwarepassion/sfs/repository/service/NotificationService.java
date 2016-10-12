package com.softwarepassion.sfs.repository.service;

import com.softwarepassion.sfs.model.Notification;
import com.softwarepassion.sfs.model.NotificationType;
import com.softwarepassion.sfs.model.User;
import com.softwarepassion.sfs.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }


    public String createResetPasswordNotification(User user) {
        Notification notification = new Notification();
        notification.setCreated(new Date());
        notification.setNotificationType(NotificationType.PASSWORD_RESET);
        notification.setUser(user);
        final String uuid = UUID.randomUUID().toString();
        notification.setUuid(uuid);
        notificationRepository.save(notification);
        return uuid;
    }

    public Optional<Notification> findByUuid(String uuid){
        return notificationRepository.findByUuid(uuid);
    }
}
