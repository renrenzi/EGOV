package com.egov.service;

import com.egov.entity.En_Inv;
import com.egov.entity.EnterPrise;

import java.util.List;

/**
 * @author JJ
 * @date 2021/5/12  - {TIME}
 */
public interface EnterpriseService {
    int saveEnterPrise(EnterPrise enterPrise, List<En_Inv> en_invList);
}
