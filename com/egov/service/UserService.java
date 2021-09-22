package com.egov.service;

import com.egov.entity.User;

/**
 * @author JJ
 * @date 2021/5/12  - {TIME}
 */
public interface UserService {
     int saveUser(User user);
     User UpdateUser(User user);
     int update(User user);
}
