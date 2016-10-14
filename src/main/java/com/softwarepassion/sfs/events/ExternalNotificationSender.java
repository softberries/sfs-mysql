package com.softwarepassion.sfs.events;


import com.softwarepassion.sfs.model.Notification;

public interface ExternalNotificationSender {

    void sendNotification(Notification notification);
}
