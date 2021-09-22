package com.egov.dao.impl;

import com.egov.dao.EnterpriseDao;
import com.egov.entity.En_Inv;
import com.egov.entity.EnterPrise;
import com.egov.util.JdbcUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author JJ
 * @date 2021/5/12  - {TIME}
 */
public class EnterpriseDaoImpl implements EnterpriseDao {
    private JdbcUtil util = new JdbcUtil();
    public boolean checkOrgCode (String orgcode){
        ResultSet rs = null;
        Boolean result = false;
        String sql = "select * from t_enterprise where orgcode = ?";
        PreparedStatement ps = util.createStatement(sql);
        try {
            rs = ps.executeQuery();
            if (rs.next()){
                result = true;
            }
        } catch (SQLException throwables) {
            //throwables.printStackTrace();
        }
        return result;
    }
    @Override
    public int insertEn_Inv(EnterPrise enterPrise, List<En_Inv> en_invList) {
        int result = 0;
        try {
            String sql = "insert into t_enterprise(orgcode,regno,cnname,enname,contactman,contacttel,regcap,outregcap,regcry,usercode,regdate)" +
                    " values(?,?,?,?,?,?,?,?,?,?,now())";
            PreparedStatement ps = util.createStatement(sql);
            ps = util.createStatement(sql);
            ps.setString(1,enterPrise.getOrgcode());
            ps.setString(2,enterPrise.getRegno());
            ps.setString(3,enterPrise.getCnname());
            ps.setString(4,enterPrise.getEnname());
            ps.setString(5,enterPrise.getContactman());
            ps.setString(6,enterPrise.getContacttel());
            ps.setString(7,enterPrise.getRegcap());
            ps.setString(8,enterPrise.getOutregcap());
            ps.setString(9,enterPrise.getRegcry());
            ps.setString(10,enterPrise.getUsercode());
            result = ps.executeUpdate();

            sql = "insert into t_en_inv(orgcode,invregnum,regcap,scale) values(?,?,?,?)";
            ps = util.createStatement(sql);
            for (En_Inv en_inv: en_invList){
                ps.setString(1,enterPrise.getOrgcode());
                ps.setString(2,en_inv.getInvregnum());
                ps.setString(3,en_inv.getRegcap());
                ps.setString(4,en_inv.getScale());
                result += ps.executeUpdate();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            new RuntimeException("插入出现异常");
        }
        return result;
    }


}
