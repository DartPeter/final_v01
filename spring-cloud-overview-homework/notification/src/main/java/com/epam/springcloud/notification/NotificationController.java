package com.epam.springcloud.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping
@RestController
public class NotificationController {

    @Autowired
    DiscoveryClient discoveryClient;

    private final Set<Notification> notifications = new HashSet<>();

    public Notification notify(String user) {
        return new Notification();
    }

    public List<Notification> getNotifications() {
        return Collections.emptyList();
    }

    @PostMapping("/{userName}")
    public ResponseEntity<Notification> createNewNotification(@PathVariable String userName) {
        Notification notification = new Notification();
        notification.setUser(userName);
        notifications.add(notification);
        return new ResponseEntity<>(notification, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Notification> getNotifications2() {
        return new ArrayList<>(notifications);
    }

}
