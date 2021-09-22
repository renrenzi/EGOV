package com.egov.comtroller.fe;

import com.egov.dao.EnterpriseDao;
import com.egov.entity.En_Inv;
import com.egov.entity.EnterPrise;
import com.egov.entity.User;
import com.egov.service.EnterpriseService;
import com.egov.service.impl.EnterpriseServiceImpl;
import com.egov.util.JdbcUtil;
import com.egov.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JJ
 * @date 2021/5/11  - {TIME}
 */
public class SaveEnServlet extends HttpServlet {
    private EnterpriseService enterpriseService = new EnterpriseServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int result = 0;
        EnterPrise enterPrise = new EnterPrise();
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("user");
        WebUtil.makeRequestToObject(request,enterPrise);
        enterPrise.setUsercode(user.getUsercode());

        String [] invnums = request.getParameterValues("invnum");
        String [] recapItems = request.getParameterValues("recapItem");
        String [] scales = request.getParameterValues("scale");
        List <En_Inv> en_invList = new ArrayList<En_Inv>();
        for (int i = 0; i < invnums.length; i++) {
            En_Inv en_inv = new En_Inv(invnums[i],recapItems[i],scales[i]);
            en_invList.add(en_inv);
        }

        result = enterpriseService.saveEnterPrise(enterPrise,en_invList);

        if (result == 1 + invnums.length){
            request.getRequestDispatcher("/foreignExchange/newInputOrg.jsp").forward(request,response);
        }

    }
}
