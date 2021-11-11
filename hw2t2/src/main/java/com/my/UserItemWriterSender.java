package com.my;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

public class UserItemWriterSender implements ItemWriter<User> {

	@Override
	public void write(List<? extends User> items) throws Exception {
		for (User user : items) {
			LoggerFactory.getLogger(UserItemWriterSender.class).info("Sending email to " + user.getEmail());
		}
	}

}
