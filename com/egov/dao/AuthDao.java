package com.egov.dao;

import com.egov.entity.EnterPrise;
import com.egov.entity.Page;

import java.util.Map;

/**
 * @author JJ
 * @date 2021/5/13  - {TIME}
 */
public interface AuthDao {
    Page<EnterPrise> pageQuerEn(Map<String, String> conditionMap);
    EnterPrise enDetail(EnterPrise enterPrise);
}
