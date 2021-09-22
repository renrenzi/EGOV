package com.egov.comtroller.user;

import com.egov.dao.UserDao;
import com.egov.dao.impl.UserDaoImpl;
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
 * @date 2021/4/25  - {TIME}
 */
public class PageQueryUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("gb2312");
        Page <User> page = new Page<User>(request.getParameter("pageNum"));
        UserDao dao = new UserDaoImpl();
        //查询用户信息
        List userList = dao.selectUser(page.getPageNum(),page.getPageSize());
        //查询用户总数
        page.setTotalSize(dao.totalSize());
        //将用户信息添加到dateList集合中
        request.setAttribute("page",page);
        request.setAttribute("userList",userList);
        request.getRequestDispatcher("/system/user.jsp").forward(request,response);
    }
}
