package com.softwarepassion.sfs.repository.service;


import com.softwarepassion.sfs.model.Notification;
import com.softwarepassion.sfs.model.NotificationType;
import com.softwarepassion.sfs.model.User;
import com.softwarepassion.sfs.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NotificationServiceTest {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldSaveNotificationForResettingPassword() {
        //given
        Optional<User> user = userRepository.findByEmail("kgrajek@gmail.com");
        //when
        String uuid = notificationService.createResetPasswordNotification(user.get());
        Optional<Notification> notificationOpt = notificationService.findByUuid(uuid);
        //then
        assertTrue(notificationOpt.isPresent());
        assertTrue(notificationOpt.get().getUuid().equals(uuid));
        assertTrue(notificationOpt.get().getNotificationType().equals(NotificationType.PASSWORD_RESET));
    }
}