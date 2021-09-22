package com.egov.comtroller.auth;

import com.egov.entity.EnterPrise;
import com.egov.entity.Invest;
import com.egov.entity.Page;
import com.egov.service.AuthService;
import com.egov.service.impl.AuthServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

/**
 * @author JJ
 * @date 2021/5/13  - {TIME}
 */
public class PageQueryEnServlet extends HttpServlet {
    private AuthService authService = new AuthServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orgcode = request.getParameter("orgcode");
        String cnname = request.getParameter("cnname");
        String startdate = request.getParameter("startdate");
        String enddate = request.getParameter("enddate");
        String pageNum = request.getParameter("pageNum");

        Map<String,String> conditonMap = new HashMap<>();
        conditonMap.put("orgcode",orgcode);
        conditonMap.put("cnname",cnname);
        conditonMap.put("startdate",startdate);
        conditonMap.put("enddate",enddate);
        conditonMap.put("pageNum",pageNum);

        Page page = authService.pageQuerEn(conditonMap);
        request.setAttribute("Page",page);
        request.getRequestDispatcher("/auth/orgcodeSelect.jsp").forward(request,response);
    }
}
