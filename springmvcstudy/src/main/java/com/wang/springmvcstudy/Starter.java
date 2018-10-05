package com.wang.springmvcstudy;

import org.springframework.web.SpringServletContainerInitializer;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

public class Starter implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {

        int i = 1;
        i++;
    }
}
