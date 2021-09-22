package com.egov.comtroller.user;

import com.egov.dao.UserDao;
import com.egov.entity.Invest;
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
 * @date 2021/4/24  - {TIME}
 */
public class UserServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = new User();
        WebUtil.makeRequestToObject(request, user);
        //System.out.println(user.getUsercode());
        int result = userService.saveUser(user);

        response.setContentType("text/html;charset=gb2312");
        String message = "用户已存在，请重新输入";
        if (result == 1) {
            response.sendRedirect("/EGov/pageQueryUser");
        }
        else {
            request.setAttribute("message", message);
            request.getRequestDispatcher("/system/userAdd.jsp").forward(request, response);
        }
    }
}
