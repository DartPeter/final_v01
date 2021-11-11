package com.hw2.p1.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.MessageChannel;

/**
 * Configuration provider class for Spring Container.
 * 
 * @author Eren Avsarogullari
 * @since 10 Dec 2014
 * @version 1.0.0
 */
@Configuration
@ComponentScan("com.hw2.p1.integration")
@EnableIntegration
@IntegrationComponentScan("com.hw2.p1.integration")
public class AppConfiguration {

	@Bean
	public MessageChannel orderGWDefaultRequestChannel() {
		return new DirectChannel();
	}

	@Bean
	public MessageChannel orderSplitterOutputChannel() {
		return new DirectChannel();
	}

	@Bean
	public MessageChannel orderFilterOutputChannel() {
		return new DirectChannel();
	}

	@Bean
	public MessageChannel orderFilterDiscardChannel() {
		return new DirectChannel();
	}
}
