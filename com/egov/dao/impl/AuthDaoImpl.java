package com.egov.dao.impl;

import com.egov.dao.AuthDao;
import com.egov.entity.EnterPrise;
import com.egov.entity.Invest;
import com.egov.entity.Page;
import com.egov.util.JdbcUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author JJ
 * @date 2021/5/13  - {TIME}
 */
public class AuthDaoImpl implements AuthDao {
    private JdbcUtil util = new JdbcUtil();
    @Override
    public Page<EnterPrise> pageQuerEn(Map<String, String> conditionMap) {
        Page<EnterPrise> page = new Page<EnterPrise>(conditionMap.get("pageNum"));
        ResultSet rs;
        int beginIndex = page.getPageSize() * (page.getPageNum() - 1);
        int endIndex   = page.getPageSize();
        List enList =new ArrayList();
        List<String> paramList = new ArrayList<String>();

        StringBuilder sql =new StringBuilder("select e.orgcode,e.cnname,e.regdate,u.username from t_enterprise e join t_user u on e.usercode = u.usercode where 1=1 ") ;
        StringBuilder totalsql =new StringBuilder("select count(*) as totalsize from t_enterprise e join t_user u on e.usercode = u.usercode where 1=1 ") ;
        if (conditionMap.get("orgcode") != null && conditionMap.get("orgcode").trim().length() != 0) {
                sql.append(" and e.orgcode = ?");
            totalsql.append(" and e.orgcode = ?");
                paramList.add(conditionMap.get("orgcode"));
        }
        if (conditionMap.get("cnname") != null && conditionMap.get("cnname").trim().length() != 0) {
                sql.append(" and e.cnname like ?");
            totalsql.append(" and e.cnname like ?");
                paramList.add("%" +conditionMap.get("cnname")+ "%");
        }
        if (conditionMap.get("startdate") != null && conditionMap.get("startdate").trim().length() != 0) {
                sql.append(" and e.regdate >= ?");
            totalsql.append(" and e.regdate >= ?");
                paramList.add(conditionMap.get("startdate"));
        }
        if (conditionMap.get("enddate") != null && conditionMap.get("enddate").trim().length() != 0) {
                sql.append(" and i.regdate <= ?");
            totalsql.append(" and i.regdate <= ?");
                paramList.add(conditionMap.get("enddate"));
        }
        sql.append(" order by e.regdate desc  limit "+beginIndex+","+endIndex+"");
        PreparedStatement ps = util.createStatement(sql.toString());
        try {
                for (int i = 0; i < paramList.size(); i++) {
                    ps.setString(i+1,paramList.get(i));
                }

                rs = ps.executeQuery();
                while (rs.next()){
                    String orgcode =  rs.getString("orgcode");
                    String cnname =  rs.getString("cnname");
                    String regdate =  rs.getString("regdate");
                    String username =  rs.getString("username");
                    EnterPrise enterPrise = new EnterPrise(orgcode,cnname,regdate,username);
                    enList.add(enterPrise);
                }
                page.setDateList(enList);
                ps = util.createStatement(totalsql.toString());
                rs = ps.executeQuery();
                for (int i = 0; i < paramList.size(); i++) {
                    ps.setString(i+1,paramList.get(i));
                }
                if (rs.next()){
                    page.setTotalSize(rs.getInt("totalsize"));
                }
            }
            catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                util.close();
            }
            return page;
        }

    @Override
    public EnterPrise enDetail(EnterPrise enterPrise) {
        String sql = "select orgcode,cnname,regcap,regcry from t_enterprise where orgcode = ?";
        PreparedStatement ps = util.createStatement(sql);
        ResultSet rs = null;

        try {
            ps.setString(1,enterPrise.getOrgcode());
            rs = ps.executeQuery();
            while (rs.next()){
                String orgcode =  rs.getString("orgcode");
                String cnname =  rs.getString("cnname");
                String regcap = rs.getString("regcap");
                String regcry = rs.getString("regcry");
                enterPrise = new EnterPrise(orgcode,cnname,regcap,regcry,null);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return enterPrise;
    }
}

