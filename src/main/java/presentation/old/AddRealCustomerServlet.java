package presentation.old;

import business.old.RealCustomerController;
import util.old.RealCustomerHTMLCreator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

/**
 * Created by m.farsiabi on 6/20/2016.
 */
public class AddRealCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = new String(request.getParameter("firstName").getBytes("iso-8859-1"), "utf-8");
        String lastName = new String(request.getParameter("lastName").getBytes("iso-8859-1"), "utf-8");
        String fatherName = new String(request.getParameter("fatherName").getBytes("iso-8859-1"), "utf-8");
        Date dateOfBirth = Date.valueOf(request.getParameter("dateOfBirth"));
        String nationalId = request.getParameter("nationalId");

        int resultCode = RealCustomerController.getInstance().addCustomer(firstName, lastName, fatherName, dateOfBirth, nationalId);
        if (resultCode == 0) {
            response.sendRedirect("/realCustomersHome");
        } else {
            response.setContentType("text/html;charset=utf-8");
            response.setCharacterEncoding("UTF-8");

            request.setCharacterEncoding("UTF-8");

            PrintWriter out = response.getWriter();
            out.println(RealCustomerHTMLCreator.addCustomer(firstName, lastName, fatherName, dateOfBirth.toString(), nationalId, true));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");

        request.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        String firstName = (request.getParameterMap().containsKey("firstName")) ? new String(request.getParameter("firstName").getBytes("iso-8859-1"), "utf-8") : "";
        String lastName = (request.getParameterMap().containsKey("lastName")) ? new String(request.getParameter("lastName").getBytes("iso-8859-1"), "utf-8") : "";
        String fatherName = (request.getParameterMap().containsKey("fatherName")) ? new String(request.getParameter("fatherName").getBytes("iso-8859-1"), "utf-8") : "";
        String dateOfBirth = (request.getParameterMap().containsKey("dateOfBirth")) ? request.getParameter("dateOfBirth") : "";
        String nationalId = (request.getParameterMap().containsKey("nationalId")) ? request.getParameter("nationalId") : "";

        out.println(RealCustomerHTMLCreator.addCustomer(firstName,lastName,fatherName,dateOfBirth,nationalId,false));
    }
}