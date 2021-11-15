package com.my;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;  
import org.aspectj.lang.annotation.Before;  
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;  
  
@Aspect  
public class TrackOperation{  
    @Pointcut("execution(* Operation.*(..))")  
    public void k(){}  
      
    @Before("k()")  
    public void myadviceBefore(JoinPoint jp) {
        LoggerFactory.getLogger(TrackOperation.class).info("Calling before "  + jp.getSignature());  
    }
    
    @After("k()")  
    public void myadviceAfter(JoinPoint jp) {
        LoggerFactory.getLogger(TrackOperation.class).info("Calling after "  + jp.getSignature());  
    }
}  
