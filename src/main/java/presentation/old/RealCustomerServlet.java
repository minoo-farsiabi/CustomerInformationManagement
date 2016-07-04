package presentation.old;


import business.old.RealCustomerController;
import util.old.RealCustomerHTMLCreator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

/**
 * Created by m.farsiabi on 6/20/2016.
 */
public class RealCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        response.setCharacterEncoding("UTF-8");

        request.setCharacterEncoding("UTF-8");

        String whichButtonClicked = request.getParameter("act");

        System.out.println("in doPost of presentation.old.RealCustomerServlet + " + whichButtonClicked);

        if (whichButtonClicked.contains("deleteCustomer")) {
            BigDecimal customerNumber = new BigDecimal(whichButtonClicked.split("&")[1]);

            RealCustomerController.getInstance().deleteCustomer(customerNumber);
        }

        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        response.setCharacterEncoding("UTF-8");

        request.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.println(RealCustomerHTMLCreator.customerManagement());
    }


}
