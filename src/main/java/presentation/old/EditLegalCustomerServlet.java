package presentation.old;

import business.old.LegalCustomerController;
import model.entity.old.LegalCustomerEntity;
import util.old.LegalCustomerHTMLCreator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by m.farsiabi on 6/21/2016.
 */
public class EditLegalCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BigDecimal customerNumber = new BigDecimal(request.getParameter("customerNumber"));
        String companyName = new String(request.getParameter("companyName").getBytes("iso-8859-1"), "utf-8");
        Date dateOfConfirmation = Date.valueOf(request.getParameter("dateOfConfirmation"));
        String economicalId = request.getParameter("economicalId");

        LegalCustomerController.getInstance().editCustomer(customerNumber,companyName,dateOfConfirmation,economicalId);
        response.sendRedirect("/legalCustomersHome");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        request.setCharacterEncoding("UTF-8");

        BigDecimal customerNumber = new BigDecimal(request.getParameter("customerNumber"));

        LegalCustomerEntity legalCustomerEntity = LegalCustomerController.getInstance().findCustomerByCustomerNumber(customerNumber);

        response.setContentType("text/html;charset=utf-8");

        PrintWriter out = response.getWriter();
        out.println(LegalCustomerHTMLCreator.editCustomer(legalCustomerEntity));
    }
}
