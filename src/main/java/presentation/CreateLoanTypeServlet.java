package presentation;

import business.LoanTypeController;
import model.entity.GrantConditionEntity;
import model.entity.LoanTypeEntity;
import util.LoanUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by m.farsiabi on 6/26/2016.
 */
public class CreateLoanTypeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameterMap().containsKey("status") && request.getParameter("status").equals("addLoanType")) {
            addLoanType(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/CreateLoanType.jsp");
        rd.forward(request, response);
    }

    private void addLoanType (HttpServletRequest request, HttpServletResponse response) throws IOException {
        String loanTypeName = new String(request.getParameter("loanTypeName").getBytes("iso-8859-1"), "utf-8");
        int interestRate = Integer.parseInt(request.getParameter("interestRate"));
        LoanTypeEntity loanTypeEntity = LoanTypeController.getInstance().getLoanTypeByName(loanTypeName);
        if (loanTypeEntity != null) {
            String message = "نام تسهیلات تکراری است";
            String errorMessage = new String(message.getBytes("utf-8"),"iso-8859-1");
            response.sendRedirect("/problemFoundServlet?errorMessage=" + errorMessage + "&parentURL=createLoanType");
        }
        else {
            LoanUtil.getInstance().setCurrentLoanTypeEntity(new LoanTypeEntity(loanTypeName,interestRate));
            response.sendRedirect("/addGrantCondition?status=init");
        }
    }
}
