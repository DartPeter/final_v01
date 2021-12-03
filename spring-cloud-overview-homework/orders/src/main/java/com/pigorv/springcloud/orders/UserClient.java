package com.pigorv.springcloud.orders;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "users", fallback = UserClient.UserClientImpl.class)
public interface UserClient {

    @GetMapping("/{userName}")
    ResponseEntity<UserDto> getUser(@PathVariable String userName);

    @Component
    class UserClientImpl implements UserClient {
        @Override
        public ResponseEntity<UserDto> getUser(String userName) {
            UserDto userDto = new UserDto();
            userDto.setName("Something went wrong");
            return new ResponseEntity<>(userDto, HttpStatus.BAD_GATEWAY);
        }
    }

}
