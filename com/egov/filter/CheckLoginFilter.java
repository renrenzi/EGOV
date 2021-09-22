package com.egov.filter;

import com.egov.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.midi.Soundbank;
import java.io.IOException;

/**
 * @author JJ
 * @date 2021/5/10  - {TIME}
 */
public class CheckLoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setContentType("text/html;charset=gb2312");
        HttpSession httpSession = request.getSession(false);
        String servletPath = request.getServletPath();

        if ("//login.jsp".equals(servletPath) || "/loginServlet".equals(servletPath) ||(httpSession != null  && httpSession.getAttribute("user") != null)){
            filterChain.doFilter(servletRequest,servletResponse);
        }
        else {
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            //response.sendRedirect("/EGov/login.jsp");
        }

    }

    @Override
    public void destroy() {

    }
}
