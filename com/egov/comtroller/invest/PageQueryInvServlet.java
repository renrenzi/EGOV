package com.egov.comtroller.invest;

import com.egov.dao.InvestDao;
import com.egov.dao.UserDao;
import com.egov.entity.Invest;
import com.egov.entity.Page;
import com.egov.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author JJ
 * @date 2021/5/7  - {TIME}
 */
public class PageQueryInvServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String invnum = request.getParameter("invnum");
        String invname = request.getParameter("invname");
        String startdate = request.getParameter("startdate");
        String enddate = request.getParameter("enddate");

        InvestDao dao = new InvestDao();
        Page<Invest> page = new Page<Invest>(request.getParameter("pageNum"));

        //查询用户信息
        List investList = dao.selectInvest(page.getPageNum(),page.getPageSize(),invnum,invname,startdate,enddate);
        //查询用户总数
        page.setTotalSize(dao.totalSize(invnum,invname,startdate,enddate));
        //将用户信息添加到dateList集合中
        request.setAttribute("page",page);
        request.setAttribute("investList",investList);

        String goPage = request.getParameter("goPage");
        if ("1".equals(goPage)){
            request.getRequestDispatcher("/basicinfo/exoticOrgList.jsp").forward(request,response);
        }
        else if ("2".equals(goPage)){
            request.getRequestDispatcher("/foreignExchange/orgcodeSelect.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {




    }
}
