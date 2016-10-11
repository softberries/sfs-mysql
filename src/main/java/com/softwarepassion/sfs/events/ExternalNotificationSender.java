package com.softwarepassion.sfs.events;


import com.softwarepassion.sfs.model.Notification;
import com.softwarepassion.sfs.model.User;

public interface ExternalNotificationSender {

    void sendNotification(User user, Notification notification);
}
