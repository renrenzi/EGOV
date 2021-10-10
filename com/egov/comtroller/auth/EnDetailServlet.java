package com.egov.comtroller.auth;

import com.egov.entity.EnterPrise;
import com.egov.service.AuthService;
import com.egov.service.impl.AuthServiceImpl;
import com.egov.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author JJ
 * @date 2021/5/13  - {TIME}
 */
public class EnDetailServlet extends HttpServlet {
    AuthService authService = new AuthServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EnterPrise enterPrise = new EnterPrise();
        WebUtil.makeRequestToObject(request,enterPrise);

        enterPrise = authService.enDetail(enterPrise);
        if (enterPrise != null){
            request.setAttribute("enterPrise",enterPrise);
            request.getRequestDispatcher("/auth/openAccountAuthDetail.jsp").forward(request,response);

        }
    }
}
