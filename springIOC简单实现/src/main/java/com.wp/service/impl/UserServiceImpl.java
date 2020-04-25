package com.wp.service.impl;

import com.wp.dao.UserDao;
import com.wp.service.UserService;

/**
 * Created by 王萍 on 2017/2/10 0010.
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void say() {
        userDao.say();
    }
}
