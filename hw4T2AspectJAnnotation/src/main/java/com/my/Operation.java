package com.my;

import org.slf4j.LoggerFactory;

public class Operation {
	public void msg() {
		LoggerFactory.getLogger(Operation.class).info("msg method invoked");
	}
}
