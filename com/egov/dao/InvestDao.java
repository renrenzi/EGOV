package com.egov.dao;

import com.egov.entity.Invest;
import com.egov.entity.User;
import com.egov.util.JdbcUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JJ
 * @date 2021/4/29  - {TIME}
 */
public class InvestDao {
    private JdbcUtil util  = new JdbcUtil();
    //插入投资人信息
   public int insertInvest(String invname,String cty,String orgcode,String contactman ,
                           String contacttel ,String email,String remark ,String usercode){
       int result = 0;
       try {
           String sql = "insert into t_invest(invname,cty,orgcode,contactman,contacttel,email,remark,usercode,regdate)"+ " values(?,?,?,?,?,?,?,?,now())";
           PreparedStatement ps = util.createStatement(sql);
           ps.setString(1,invname);
           ps.setString(2,cty);
           ps.setString(3,orgcode);
           ps.setString(4,contactman);
           ps.setString(5,contacttel);
           ps.setString(6,email);
           ps.setString(7,remark);
           ps.setString(8,usercode);
           result = ps.executeUpdate();
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }finally {
           util.close();
       }
       return result;
    }
    public List selectInvest(int pageNum, int pageSize,String invnum,String invname,String startdate,String enddate){
        ResultSet rs;
        int beginIndex = pageSize * (pageNum - 1);
        int endIndex   = pageSize ;
        List investList =new ArrayList();
        List<String> paramList = new ArrayList<String>();

        StringBuilder sql =new StringBuilder("select i.invnum,i.invname,i.regdate,i.cty,u.username from t_invest i join t_user u on i.usercode = u.usercode where 1=1 ") ;
        if (invnum != null && invnum.trim().length() != 0) {
            sql.append(" and i.invnum = ?");
            paramList.add(invnum);
        }
        if (invname != null && invname.trim().length() != 0) {
            sql.append(" and i.invname like ?");
            paramList.add("%" +invname+ "%");
        }
        if (startdate != null && startdate.trim().length() != 0) {
            sql.append(" and i.regdate >= ?");
            paramList.add(startdate);
        }
        if (enddate != null && enddate.trim().length() != 0) {
            sql.append(" and i.regdate <= ?");
            paramList.add(enddate);
        }
        //order by regdate desc  limit "+beginIndex+","+endIndex+"
        //select i.invnum,i.invname,i.regdate,i.cty,i.username from t_invest i join t_user t on i.usercode = u.usercode where 1=1
        sql.append(" order by i.regdate desc  limit "+beginIndex+","+endIndex+"");
        PreparedStatement ps = util.createStatement(sql.toString());
        try {
            for (int i = 0; i < paramList.size(); i++) {
                ps.setString(i+1,paramList.get(i));
            }

            rs = ps.executeQuery();
            while (rs.next()){
                invnum =  rs.getString("invnum");
                invname =  rs.getString("invname");
                String regdate =  rs.getString("regdate");
                String cty =  rs.getString("cty");
                String username =  rs.getString("username");
                Invest invest = new Invest(invnum,invname,cty,regdate,username);
                investList.add(invest);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            util.close();
        }

        return investList;
    }
    public int  totalSize(String invnum,String invname,String startdate,String enddate){
        ResultSet rs;
        int totalSize = 0;
        List<String> paramList = new ArrayList<String>();
        StringBuilder totalSizesql =new StringBuilder("select count(*)  as totalSize from t_invest i join t_user u on i.usercode = u.usercode where 1=1 ") ;
        if (invnum != null && invnum.trim().length() != 0) {
            totalSizesql.append(" and i.invnum = ?");
            paramList.add("invnum");
        }
        if (invname != null && invname.trim().length() != 0) {
            totalSizesql.append(" and i.invname like ?");
            paramList.add(" % "+invnum+" % ");
        }
        if (startdate != null && startdate.trim().length() != 0) {
            totalSizesql.append(" and i.regdate >= ?");
            paramList.add("startdate");
        }
        if (enddate != null && enddate.trim().length() != 0) {
            totalSizesql.append(" and i.regdate <= ?");
            paramList.add("enddate");
        }
        //select count*  as totalSize from t_invest i join t_user u on i.usercode = u.usercode where 1=1  and i.invnum = ?
        PreparedStatement ps = util.createStatement(totalSizesql.toString());
        try {
            for (int i = 0; i < paramList.size(); i++) {
                ps.setString(i+1,paramList.get(i));
            }
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
    public Invest allInvest(String invnum){
        ResultSet rs;

        String sql = "select invname,cty,orgcode,contactman,contacttel,email,remark,usercode,regdate from t_invest where invnum = ?";
        PreparedStatement ps = util.createStatement(sql);
        Invest invest  = null;
        try {
            ps.setString(1,invnum);
            rs = ps.executeQuery();
            while (rs.next()){
                String invname =  rs.getString("invname");
                String cty =  rs.getString("cty");
                String orgcode =  rs.getString("orgcode");
                String contactman =  rs.getString("contactman");
                String contacttel =  rs.getString("contacttel");
                String email =  rs.getString("email");
                String regdate =  rs.getString("regdate");
                String usercode =  rs.getString("usercode");
                String remark =  rs.getString("remark");
                invest = new Invest(invname,cty,orgcode,contactman,contacttel,email,regdate,usercode,remark);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            util.close();
        }
        return invest;
    }
}
