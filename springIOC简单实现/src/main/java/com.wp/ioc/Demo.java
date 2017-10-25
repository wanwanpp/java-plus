package com.wp.ioc;

import com.wp.service.UserService;

public class Demo {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
                "beans.xml");
        UserService userService = (UserService) ctx.getBean("userService");
        userService.say();
    }
}