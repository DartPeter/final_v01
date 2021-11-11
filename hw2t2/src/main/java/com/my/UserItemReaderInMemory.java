package com.my;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.batch.item.ItemReader;

public class UserItemReaderInMemory implements ItemReader<User>{

	private int nextUserIndex;
    private List<User> userData;
 
    public UserItemReaderInMemory() {
        initialize();
    }
 
    private void initialize() {
    	User tony = new User("Tony Tester", "tony.tester@gmail.com", 11);
    	User nick = new User("Nick Newbie", "nick.newbie@gmail.com", 7);
    	User ian = new User("Ian Intermediate", "ian.intermediate@gmail.com", 5);
 
        userData = Collections.unmodifiableList(Arrays.asList(tony, nick, ian));
        nextUserIndex = 0;
    }
 
    @Override
    public User read() throws Exception {
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
