package com.my;

import org.springframework.stereotype.Component;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

@Component
public class CustomEndpoint implements HealthIndicator{

	@Override
	public Health health() {
        
        return Health.up()
                .withDetail("app.name", "hw3")
                .build();
	}

}
