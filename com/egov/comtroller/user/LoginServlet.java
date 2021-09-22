package com.egov.comtroller.user;

import com.egov.dao.UserDao;
import com.egov.dao.impl.UserDaoImpl;
import com.egov.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author JJ
 * @date 2021/4/29  - {TIME}
 */
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usercode = request.getParameter("usercode");
        String userpswd = request.getParameter("userpswd");
        String orgtype = request.getParameter("orgtype");
        UserDao dao = new UserDaoImpl();

        User user = dao.loginUser(usercode, userpswd, orgtype);

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("user", user);
        if (user != null) {
            response.sendRedirect("main.html");
        } else {
            String message = "登录失败请重新登录";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}