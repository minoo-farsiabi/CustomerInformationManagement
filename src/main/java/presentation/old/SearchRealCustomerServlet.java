package presentation.old;

import util.old.RealCustomerHTMLCreator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by m.farsiabi on 6/22/2016.
 */
public class SearchRealCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        response.setCharacterEncoding("UTF-8");

        request.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        String firstNameFilterText = (request.getParameterMap().containsKey("firstNameFilterText")) ? new String(request.getParameter("firstNameFilterText").getBytes("iso-8859-1"), "utf-8"): "";
        String lastNameFilterText = (request.getParameterMap().containsKey("lastNameFilterText")) ? new String(request.getParameter("lastNameFilterText").getBytes("iso-8859-1"), "utf-8") : "";
        String nationalIdFilterText = (request.getParameterMap().containsKey("nationalIdFilterText")) ? request.getParameter("nationalIdFilterText") : "";
        String realCustomerNumberFilterText = (request.getParameterMap().containsKey("realCustomerNumberFilterText")) ? request.getParameter("realCustomerNumberFilterText") : "";

        out.println(RealCustomerHTMLCreator.getAllCustomersWithFilters(firstNameFilterText, lastNameFilterText, nationalIdFilterText, realCustomerNumberFilterText));
    }
}
