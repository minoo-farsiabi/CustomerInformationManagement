package presentation.old;

import util.old.LegalCustomerHTMLCreator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by m.farsiabi on 6/25/2016.
 */
public class SearchLegalCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        response.setCharacterEncoding("UTF-8");

        request.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        String companyNameFilterText = (request.getParameterMap().containsKey("companyNameFilterText")) ? new String(request.getParameter("companyNameFilterText").getBytes("iso-8859-1"), "utf-8") : "";
        String economicalIdFilterText = (request.getParameterMap().containsKey("economicalIdFilterText")) ? request.getParameter("economicalIdFilterText") : "";
        String legalCustomerNumberFilterText = (request.getParameterMap().containsKey("legalCustomerNumberFilterText")) ? request.getParameter("legalCustomerNumberFilterText") : "";

        out.println(LegalCustomerHTMLCreator.getAllCustomersWithFilters(companyNameFilterText, economicalIdFilterText, legalCustomerNumberFilterText));
    }
}
