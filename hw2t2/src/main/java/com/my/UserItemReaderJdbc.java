package com.my;

import java.util.ArrayList;
import java.util.List;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserItemReaderJdbc implements ItemReader<User> {
	
	private int nextUserIndex;
	private List<User> userData = new ArrayList<>();

    public UserItemReaderJdbc(JdbcTemplate jdbcTemplate) {
    	jdbcTemplate.query("SELECT name, email, balance FROM user",
				(rs, row) -> new User(rs.getString(1), rs.getString(2), rs.getInt(3)))
		.forEach(user -> userData.add(user));
	}

	@Override
	public User read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		User nextUser = null;
        if (nextUserIndex < userData.size()) {
            nextUser = userData.get(nextUserIndex);
            nextUserIndex++;
        }
        else {
            nextUserIndex = 0;
        }
        return nextUser;
	}
	
}
