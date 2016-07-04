package presentation.old;

import business.old.RealCustomerController;
import model.entity.old.RealCustomerEntity;
import util.old.RealCustomerHTMLCreator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by m.farsiabi on 6/20/2016.
 */
public class EditRealCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BigDecimal customerNumber = new BigDecimal(request.getParameter("customerNumber"));
        String firstName = new String(request.getParameter("firstName").getBytes("iso-8859-1"), "utf-8");
        String lastName = new String(request.getParameter("lastName").getBytes("iso-8859-1"), "utf-8");
        String fatherName = new String(request.getParameter("fatherName").getBytes("iso-8859-1"), "utf-8");
        Date dateOfBirth = Date.valueOf(request.getParameter("dateOfBirth"));
        String nationalId = request.getParameter("nationalId");

        RealCustomerController.getInstance().editCustomer(customerNumber,firstName,lastName,fatherName,dateOfBirth,nationalId);
        response.sendRedirect("/realCustomersHome");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        request.setCharacterEncoding("UTF-8");

        BigDecimal customerNumber = new BigDecimal(request.getParameter("customerNumber"));

        RealCustomerEntity realCustomerEntity = RealCustomerController.getInstance().findCustomerByCustomerNumber(customerNumber);

        response.setContentType("text/html;charset=utf-8");

        PrintWriter out = response.getWriter();
        out.println(RealCustomerHTMLCreator.editCustomer(realCustomerEntity));
    }
}
