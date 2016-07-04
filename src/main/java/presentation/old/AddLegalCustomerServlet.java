package presentation.old;


import business.old.LegalCustomerController;
import util.old.LegalCustomerHTMLCreator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

/**
 * Created by m.farsiabi on 6/21/2016.
 */
public class AddLegalCustomerServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        request.setCharacterEncoding("UTF-8");

        String companyName = new String(request.getParameter("companyName").getBytes("iso-8859-1"), "utf-8");
        Date dateOfConfirmation = Date.valueOf(request.getParameter("dateOfConfirmation"));
        String economicalId = request.getParameter("economicalId");

        int resultCode = LegalCustomerController.getInstance().addCustomer(companyName, dateOfConfirmation, economicalId);
        if (resultCode == 0) {
            response.sendRedirect("/legalCustomersHome");
        } else {
            PrintWriter out = response.getWriter();
            out.println(LegalCustomerHTMLCreator.addCustomer(companyName, dateOfConfirmation.toString(), economicalId, true));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        request.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        String companyName = (request.getParameterMap().containsKey("companyName")) ? new String(request.getParameter("companyName").getBytes("iso-8859-1"), "utf-8") : "";
        String dateOfConfirmation = (request.getParameterMap().containsKey("dateOfConfirmation")) ? request.getParameter("dateOfConfirmation") : "";
        String economicalId = (request.getParameterMap().containsKey("economicalId")) ? request.getParameter("economicalId") : "";

        out.println(LegalCustomerHTMLCreator.addCustomer(companyName, dateOfConfirmation, economicalId, false));
    }
}
