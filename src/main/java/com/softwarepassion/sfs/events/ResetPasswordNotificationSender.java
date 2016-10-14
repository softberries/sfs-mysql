package com.softwarepassion.sfs.events;

import com.softwarepassion.sfs.model.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ResetPasswordNotificationSender implements ExternalNotificationSender {

    @Override
    @Async
    public void sendNotification(Notification notification) {
        log.debug("Sending external notification: " + notification);
    }
}
