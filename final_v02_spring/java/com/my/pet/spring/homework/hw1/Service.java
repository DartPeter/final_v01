package com.my.pet.spring.homework.hw1;

import org.springframework.stereotype.Component;

@Component
public class Service {

    @Timed
    public void serve() throws InterruptedException {
        Thread.sleep(1000);
    }
}