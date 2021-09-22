package com.egov.dao;

import com.egov.entity.En_Inv;
import com.egov.entity.EnterPrise;

import java.util.List;

/**
 * @author JJ
 * @date 2021/5/10  - {TIME}
 */
public interface EnterpriseDao {
    boolean checkOrgCode (String orgcode);
    int insertEn_Inv(EnterPrise enterPrise, List<En_Inv> en_invList);
}
