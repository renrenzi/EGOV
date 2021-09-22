package com.egov.comtroller.invest;

import com.egov.dao.InvestDao;
import com.egov.entity.Invest;
import com.egov.entity.User;
import com.egov.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * @author JJ
 * @date 2021/4/29  - {TIME}
 */

public class SaveInvServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("gb2312");

      String invname =  request.getParameter("invname");
      String cty    =  request.getParameter("cty");
      String orgcode    =  request.getParameter("orgcode");
      String contactman    =  request.getParameter("contactman");
      String contacttel    =  request.getParameter("contacttel");



      String email    =  request.getParameter("email");
      String remark    =  request.getParameter("remark");

      String usercode =((User) request.getSession().getAttribute("user")).getUsercode();
      InvestDao dao = new InvestDao();
      int result = dao.insertInvest(invname,cty,orgcode,contactman,contacttel,email,remark,usercode);
      if (result == 1){
          response.sendRedirect("/EGov/basicinfo/exoticOrgList.jsp");
      }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
