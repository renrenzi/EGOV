package com.egov.util;


import com.sun.org.apache.bcel.internal.generic.NEW;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.Iterator;
import java.util.Map;

// JdbcUtil obj = new JdbcUtil();  obj.getCon()
// JdbcUtil obj = new JdbcUtil();  obj.createStatement();
// JdbcUtil.getCon();
public class JdbcUtil {

     final String URL="jdbc:mysql://localhost:3306/jj";
     final String USERNAME="root";
     final String PASSWORD="******";
     public static  ThreadLocal <Connection> threadLocal = new ThreadLocal<Connection>();
     PreparedStatement ps= null;
     Connection con = null;
    //-----------通过全局作用域对象得到Connection对象
    public  Connection   getCon(HttpServletRequest request){
       ServletContext application  = request.getServletContext();
       Map map = (Map) application.getAttribute("key1");
       Iterator iterator = map.keySet().iterator();
       while (iterator.hasNext()){
           con = (Connection)iterator.next();
           boolean flag =(Boolean) map.get(con);
           if (flag == true){
                map.put(con,false);
                break;
           }
       }
       return con;
    }
    public  PreparedStatement createStatement(String sql,HttpServletRequest request){

        try {
            ps =  getCon(request).prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }
    public  void close(HttpServletRequest request){
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        ServletContext application = request.getServletContext();
        Map map = (Map) application.getAttribute("key1");
        map.put(con,true);
    }
    //将jar包中driver实现类加载到JVM中
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //封装连接通道创建细节
    public  Connection   getCon(){

        con  = threadLocal.get();
        try {
            if (con == null){
                con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                threadLocal.set(con);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    //封装交通工具创建细节
    public  PreparedStatement createStatement(String sql){

        try {
            ps =  getCon().prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }
    // ps与con销毁细节 insert,update,delete
    public  void close(){
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(con!=null){
            try {
                con.close();
                threadLocal.remove();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

     //select ps,con,rs
    public  void close(ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        close();
    }
    //开启事务
    public static void beginTransaction(Connection con) throws SQLException {
        if (con != null){
         con.setAutoCommit(false);
        }
    }
    //提交事务
    public static void commitTransaction(Connection con) throws SQLException {
        if (con != null){
         con.commit();
        }
    }
    //回滚事务
    public static void rollbackTransaction(Connection con) throws SQLException {
        if (con != null){
        con.rollback();
        }
    }
    //关闭事务
    public static void closeTransaction(Connection con) throws SQLException {
        if (con != null){
        con.setAutoCommit(true);
        }
    }
}
