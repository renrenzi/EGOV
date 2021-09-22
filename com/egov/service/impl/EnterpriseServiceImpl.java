package com.egov.service.impl;

import com.egov.dao.EnterpriseDao;
import com.egov.dao.impl.EnterpriseDaoImpl;
import com.egov.entity.En_Inv;
import com.egov.entity.EnterPrise;
import com.egov.service.EnterpriseService;
import com.egov.util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author JJ
 * @date 2021/5/12  - {TIME}
 */
public class EnterpriseServiceImpl implements EnterpriseService {

    private EnterpriseDao enterpriseDao = new EnterpriseDaoImpl();
    public int saveEnterPrise(EnterPrise enterPrise, List<En_Inv> en_invList) {
        JdbcUtil util = new JdbcUtil();
        Connection con = null;
        int result = 0;
        con = util.getCon();
        try {
            JdbcUtil.beginTransaction(con);

            result = enterpriseDao.insertEn_Inv(enterPrise,en_invList);

            JdbcUtil.commitTransaction(con);
        } catch (SQLException throwables) {
            try {
                JdbcUtil.rollbackTransaction(con);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }finally {
            try {
                JdbcUtil.closeTransaction(con);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            util.close();
        }
        return result;
    }
}
