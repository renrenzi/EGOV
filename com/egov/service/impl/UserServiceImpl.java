package com.egov.service.impl;

import com.egov.dao.UserDao;
import com.egov.dao.impl.UserDaoImpl;
import com.egov.entity.User;
import com.egov.service.UserService;

/**
 * @author JJ
 * @date 2021/5/12  - {TIME}
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public int saveUser(User user) {
       int result = 0;
       if (userDao.whetherInsertUser(user.getUsercode()) == false){
           result  = userDao.insertUser(user);
       }
       return result;
    }

    @Override
    public User UpdateUser(User user) {
        user = userDao.updateUser(user.getUsercode());
        return user;
    }

    @Override
    public int update(User user) {
        int result = userDao.update(user.getUsercode(),user.getUsername(),user.getUserpswd(),user.getOrgtype());
        return result;
    }
}
