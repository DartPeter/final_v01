package com.my;

import org.aspectj.lang.JoinPoint;
import org.slf4j.LoggerFactory;  

public class TrackOperation {
	public void myadviceBefore(JoinPoint jp){
		LoggerFactory.getLogger(TrackOperation.class).info("Calling before " + jp.getSignature());
    }
	public void myadviceAfter(JoinPoint jp){
		LoggerFactory.getLogger(TrackOperation.class).info("Calling after " + jp.getSignature());
    }
}
