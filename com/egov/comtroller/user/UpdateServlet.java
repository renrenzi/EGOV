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
 * @date 2021/4/26  - {TIME}
 */
public class UpdateServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { ;
        User user = new User();
        WebUtil.makeRequestToObject(request,user);
        user = userService.UpdateUser(user);
        request.setAttribute("user",user);
        request.getRequestDispatcher("/system/userUpdate.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
