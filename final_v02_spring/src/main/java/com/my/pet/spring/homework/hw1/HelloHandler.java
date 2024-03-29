package com.my.pet.spring.homework.hw1;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class HelloHandler {
	public Mono<ServerResponse> monoMessage(ServerRequest request) {
		return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).body(Mono.just("Welcome to JstoBigdata.com"),
				String.class);
	}
}
