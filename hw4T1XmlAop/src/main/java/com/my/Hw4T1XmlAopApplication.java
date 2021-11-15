package com.my;

import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class Hw4T1XmlAopApplication {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");  
        Operation e = (Operation) context.getBean("opBean");  
        LoggerFactory.getLogger(Hw4T1XmlAopApplication.class).info("calling msg...");  
        e.msg();
        ((ClassPathXmlApplicationContext) context).close(); 
	}

}
