package com.my.pet.spring.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.my.pet.spring.homework.hw1.HelloRouter;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  protected String[] getServletMappings() {
    return new String[]{"/"};
  }

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class[]{WebConfig.class,
    		HelloRouter.class
    		};
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return null;
  }


}
