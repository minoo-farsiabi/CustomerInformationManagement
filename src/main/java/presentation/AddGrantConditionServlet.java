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

/**
 * Created by m.farsiabi on 6/26/2016.
 */
public class AddGrantConditionServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameterMap().containsKey("status") && request.getParameter("status").equals("addGrant")) {
            addGrantConditionToCurrentLoanType(request,response);
        } else if (request.getParameterMap().containsKey("status") && request.getParameter("status").equals("final")) {
            saveFinalChanges(request,response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/AddGrantCondition.jsp");
            rd.forward(request, response);
        }
    }

    private void addGrantConditionToCurrentLoanType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String grantConditionName = new String(request.getParameter("grantConditionName").getBytes("iso-8859-1"), "utf-8");
        int minimumDurationDays = Integer.parseInt(request.getParameter("minimumDurationDays"));
        int maximumDurationDays = Integer.parseInt(request.getParameter("maximumDurationDays"));
        int minimumBalance = Integer.parseInt(request.getParameter("minimumBalance"));
        int maximumBalance = Integer.parseInt(request.getParameter("maximumBalance"));

        int resultCode = LoanTypeController.getInstance().addGrantConditionToCurrentLoanType(new GrantConditionEntity(grantConditionName, minimumDurationDays, maximumDurationDays, minimumBalance, maximumBalance));

        if (resultCode == LoanUtil.REDUNDANT_GRANT_CONDITION) {
            String message = "نام شرط اعطا تکراری است";
            String errorMessage = new String(message.getBytes("utf-8"), "iso-8859-1");
            response.sendRedirect("/problemFoundServlet?errorMessage=" + errorMessage + "&parentURL=/addGrantCondition?loanTypeName=" + LoanTypeController.getInstance().getCurrentLoanTypeEntity().getLoanTypeName() + "&interestRate=" + LoanTypeController.getInstance().getCurrentLoanTypeEntity().getInterestRate() + "&status=init");
        } else if (resultCode == 0) {
            response.sendRedirect("/addGrantCondition");
        }
    }

    private void saveFinalChanges (HttpServletRequest request, HttpServletResponse response) throws IOException {
        long resultCode = LoanTypeController.getInstance().addLoanType(LoanTypeController.getInstance().getCurrentLoanTypeEntity().getLoanTypeName(), LoanTypeController.getInstance().getCurrentLoanTypeEntity().getInterestRate());

        if (resultCode == LoanUtil.REDUNDANT_LOAN_TYPE) {
            String message = "نام تسهیلات تکراری است";
            String errorMessage = new String(message.getBytes("utf-8"),"iso-8859-1");
            response.sendRedirect("/problemFoundServlet?errorMessage=" + errorMessage + "&parentURL=createLoanType");
        }
        else if (resultCode == LoanUtil.EMPTY_GRANT_CONDITIONS) {
            String message = "امکان تعریف تسهیلات بدون تعریف شرط اعطا امکان پذیر نیست";
            String errorMessage = new String(message.getBytes("utf-8"), "iso-8859-1");
            response.sendRedirect("/problemFoundServlet?errorMessage=" + errorMessage + "&parentURL=/addGrantCondition?loanTypeName=" + LoanTypeController.getInstance().getCurrentLoanTypeEntity().getLoanTypeName() + "$interestRate=" + LoanTypeController.getInstance().getCurrentLoanTypeEntity().getInterestRate() + "$status=init");
        } else {
            for (GrantConditionEntity grantConditionEntity : LoanTypeController.getInstance().getCurrentLoanTypeEntity().getGrantConditions()) {
                LoanTypeController.getInstance().addGrantConditionToLoanType(LoanTypeController.getInstance().getLoanTypeById(resultCode), grantConditionEntity.getGrantConditionName(),
                        grantConditionEntity.getMinimumDurationDays(), grantConditionEntity.getMaximumDurationDays(), grantConditionEntity.getMinimumBalance(), grantConditionEntity.getMaximumBalance());
                LoanTypeController.getInstance().setCurrentLoanTypeEntity(new LoanTypeEntity());
            }
            response.sendRedirect("/allLoanTypes");
        }
    }
}
