package com.egov.service.impl;

import com.egov.dao.AuthDao;
import com.egov.dao.impl.AuthDaoImpl;
import com.egov.entity.EnterPrise;
import com.egov.entity.Page;
import com.egov.service.AuthService;

import java.util.Map;

/**
 * @author JJ
 * @date 2021/5/13  - {TIME}
 */
public class AuthServiceImpl implements AuthService {
    private AuthDao authDao = new AuthDaoImpl();
    @Override
    public Page<EnterPrise> pageQuerEn(Map<String, String> conditionMap) {
        Page <EnterPrise> page = authDao.pageQuerEn(conditionMap);
        return page;
    }

    @Override
    public EnterPrise enDetail(EnterPrise enterPrise) {
        enterPrise =  authDao.enDetail(enterPrise);
        return enterPrise;
    }
}
