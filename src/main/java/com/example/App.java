package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.example.config.AppConfig;

/**
 * Hello world!
 *
 */
public class App extends AbstractAnnotationConfigDispatcherServletInitializer 
{
    public static void main(String[] args) {
        @SuppressWarnings({ "unused", "resource" })
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        System.out.println("Spring Application Context initialized");
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { AppConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { AppConfig.class };
    }

    @SuppressWarnings("null")
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}