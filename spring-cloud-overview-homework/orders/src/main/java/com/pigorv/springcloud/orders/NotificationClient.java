package com.pigorv.springcloud.orders;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "notifications", fallback = NotificationClient.NotificationClientImpl.class)
public interface NotificationClient {

    @PostMapping("/{userName}")
    ResponseEntity<NotificationDto> createNewNotification(@PathVariable String userName);

    @Component
    class NotificationClientImpl implements NotificationClient {

        @Override
        public ResponseEntity<NotificationDto> createNewNotification(String userName) {
            NotificationDto notificationDto = new NotificationDto();
            notificationDto.setUser("Something went wrong");
            return new ResponseEntity<>(notificationDto, HttpStatus.BAD_GATEWAY);
        }
    }
}
