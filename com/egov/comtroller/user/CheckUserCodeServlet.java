package com.egov.comtroller.user;

import com.egov.dao.UserDao;
import com.egov.dao.impl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author JJ
 * @date 2021/4/29  - {TIME}
 */
public class CheckUserCodeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usercode = request.getParameter("usercode");
        UserDao dao = new UserDaoImpl();
        Boolean hasuser = dao.whetherInsertUser(usercode);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        if (!hasuser){
            out.print("<font color:green>用户代码不存在</font>");
        }
        else {
            out.print("<font color:red>用户代码存在</font>");
        }
    }
}
