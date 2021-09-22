package com.egov.comtroller.fe;

import com.egov.dao.EnterpriseDao;
import com.egov.dao.impl.EnterpriseDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author JJ
 * @date 2021/5/10  - {TIME}
 */
public class CheckOrgCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orgcode = request.getParameter("orgcode");
        EnterpriseDao dao = new EnterpriseDaoImpl();
         if (dao.checkOrgCode(orgcode)){
             request.setAttribute("errorMsg","该组织机构代码已存在，请重新填写");
             request.getRequestDispatcher("/foreignExchange/newInputOrg.jsp").forward(request,response);
         }
         else {
             request.getRequestDispatcher("/foreignExchange/inputOrgInfo.jsp").forward(request,response);
         }
    }


}
