package com.egov.dao.impl;

import com.egov.dao.UserDao;
import com.egov.entity.User;
import com.egov.util.JdbcUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JJ
 * @date 2021/5/12  - {TIME}
 */
public class UserDaoImpl implements UserDao {
    private JdbcUtil  util  = new JdbcUtil();
    //添加用户
    public int  insertUser(User user){
        int result = 0;
        try {
            String sql = "insert into t_user(usercode,username,userpswd,orgtype,regdate)"+ " values(?,?,?,?,now())";
            PreparedStatement ps = util.createStatement(sql);
            ps.setString(1,user.getUsercode());
            ps.setString(2,user.getUsername());
            ps.setString(3,user.getUserpswd());
            ps.setString(4,user.getOrgtype());
            result = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("插入数据出现异常");
        }finally {
            util.close();
        }
        return result;
    }

    //是否添加用户
    public boolean  whetherInsertUser(String usercode){
        boolean hasUser = false;
        ResultSet rs  = null;
        String sql = "select * from t_user where usercode = ?";
        PreparedStatement ps = util.createStatement(sql);
        try {
            ps.setString(1, usercode);
            rs = ps.executeQuery();
            if (rs.next()){
                hasUser = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("插入数据出现异常");
        }finally {
            util.close();
        }
        return hasUser;
    }
    //查询用户
    public List selectUser(int pageNum, int pageSize){
        ResultSet rs;
        int beginIndex = pageSize * (pageNum - 1);
        int endIndex   = pageSize ;
        List userList =new ArrayList();

        String sql = "select usercode,username,orgtype from t_user order by regdate desc  limit "+beginIndex+","+endIndex+"";
        PreparedStatement ps = util.createStatement(sql);
        try {
            rs = ps.executeQuery();
            while (rs.next()){
                String usercode =  rs.getString("usercode") ;
                String username =  rs.getString("username");
                String orgtype =  rs.getString("orgtype");
                User user = new User(usercode,username,null,orgtype,null);
                userList.add(user);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            util.close();
        }

        return userList;
    }
    //查询用户总人数
    public int  totalSize(){
        ResultSet rs;
        int totalSize = 0;
        String totalsql = "select count(*)  as totalSize from t_user";
        PreparedStatement ps = util.createStatement(totalsql);
        try {
            rs = ps.executeQuery();
            if (rs.next()) {
                totalSize = rs.getInt("totalSize");
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }finally {
            util.close();
        }
        return totalSize;
    }
    //查询用户信息
    public User updateUser(String userCode) {
        ResultSet rs;

        String sql = "select username,userpswd,orgtype from t_user where usercode = ?";
        PreparedStatement ps = util.createStatement(sql);
        User user  = null;
        try {
            ps.setString(1,userCode);
            rs = ps.executeQuery();
            while (rs.next()){
                String username =  rs.getString("username");
                String userpswd =  rs.getString("userpswd");
                String orgtype =  rs.getString("orgtype");
                user = new User(userCode,username,userpswd,orgtype,null);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();

        }finally {
            util.close();
        }
        return user;
    }
    //更新用户信息
    public int update(String usercode,String username,String userpswd,String orgtype){
        int result = 0;
        String sql = "update t_user set username = ?,userpswd = ?,orgtype = ? where usercode = ?";
        PreparedStatement ps = util.createStatement(sql);
        try {
            ps.setString(1,username);
            ps.setString(2,userpswd);
            ps.setString(3,orgtype);
            ps.setString(4,usercode);
            result = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            util.close();
        }
        return result;
    }
    //删除用户信息
    public boolean  deleteUser(String [] usercodes) {
        ResultSet rs;
        boolean result = true;
        String sql = "delete from t_user where usercode = ?";
        PreparedStatement ps = util.createStatement(sql);
        try {
            for (String usercode : usercodes){
                ps.setString(1,usercode);
                ps.addBatch();
            }
            ps.executeBatch();
        }
        catch (SQLException throwables){
            result = false;
            throwables.printStackTrace();
        }finally {
            util.close();
        }
        return result;
    }
    //验证用户信息
    public User loginUser(String usercode,String userpswd,String orgtype){
        ResultSet rs;
        String sql = "select * from t_user where userpswd = ? and orgtype = ? and usercode = ?";
        PreparedStatement ps = util.createStatement(sql);
        User user  = null;
        try {
            ps.setString(1,userpswd);
            ps.setString(2,orgtype);
            ps.setString(3,usercode);
            rs = ps.executeQuery();
            while (rs.next()){
                String username =  rs.getString("username");
                user = new User(usercode,username,userpswd,orgtype,null);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            util.close();
        }
        return user;
    }
}
