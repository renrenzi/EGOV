package com.egov.comtroller.invest;

import com.egov.dao.InvestDao;
import com.egov.entity.Invest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author JJ
 * @date 2021/5/9  - {TIME}
 */
public class SelectInvestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     String invnum = request.getParameter("invnum");
     InvestDao dao = new InvestDao();
     Invest invest = dao.allInvest(invnum);
     request.setAttribute("invest",invest);
     request.getRequestDispatcher("/basicinfo/exoticOrgView.jsp").forward(request,response);
    }
}
