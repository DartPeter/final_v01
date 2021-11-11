package com.my;

import org.springframework.batch.item.ItemProcessor;

public class UserItemProcessor implements ItemProcessor<User, User>{

	@Override
	public User process(User item) throws Exception {
		if (item.getBalance() < 10) {
			return item;
		} else {
			return null;
		}
	}

}
