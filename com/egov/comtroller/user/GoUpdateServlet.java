package com.egov.comtroller.user;

import com.egov.dao.UserDao;
import com.egov.dao.impl.UserDaoImpl;
import com.egov.entity.User;
import com.egov.service.UserService;
import com.egov.service.impl.UserServiceImpl;
import com.egov.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author JJ
 * @date 2021/4/27  - {TIME}
 */
public class GoUpdateServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        int result = 0;
        User  user = new User();
        WebUtil.makeRequestToObject(request,user);

        response.setContentType("text/html;charset=gb2312");
        result = userService.update(user);
        if (result == 1){
            response.sendRedirect("/EGov/pageQueryUser?pageNum=" +request.getParameter("pageNum"));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
