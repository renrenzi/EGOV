package com.egov.dao;


import com.egov.entity.User;

import java.util.List;

/**
 * @author JJ
 * @date 2021/4/24  - {TIME}
 */
public interface UserDao {

     int  insertUser(User user);

     boolean  whetherInsertUser(String usercode);

     List selectUser(int pageNum, int pageSize);

     int  totalSize();

     User updateUser(String userCode);

     int update(String usercode,String username,String userpswd,String orgtype);

     boolean  deleteUser(String [] usercodes);

     User loginUser(String usercode,String userpswd,String orgtype);
}
