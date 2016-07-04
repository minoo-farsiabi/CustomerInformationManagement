package presentation;

import business.LoanTypeController;
import business.old.RealCustomerController;
import model.entity.old.RealCustomerEntity;
import util.LoanUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

/**
 * Created by m.farsiabi on 7/2/2016.
 */
public class CreateLoanFileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameterMap().containsKey("getInfo")) {
            getCustomerInfo(request,response);
        } else if (request.getParameterMap().containsKey("createLoanFile")) {
            createLoanFile(request,response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/CreateLoanFile.jsp");
            rd.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/CreateLoanFile.jsp");
        rd.forward(request, response);
    }

    private void getCustomerInfo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RealCustomerEntity realCustomerEntity = RealCustomerController.getInstance().findCustomerByCustomerNumber(new BigDecimal(request.getParameter("customerNumberField")));

        if (realCustomerEntity == null) {
            String message = "مشتری یافت نشد";
            String errorMessage = new String(message.getBytes("utf-8"), "iso-8859-1");
            response.sendRedirect("/problemFoundServlet?errorMessage=" + errorMessage + "&parentURL=createLoanFile");
        } else {
            request.setAttribute("firstNameField", realCustomerEntity.getFirstname());
            request.setAttribute("lastNameField", realCustomerEntity.getLastname());

            RequestDispatcher rd = request.getRequestDispatcher("/CreateLoanFile.jsp");
            rd.forward(request, response);
        }
    }

    private void createLoanFile (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String selectedLoanType = new String(request.getParameter("selectedLoanType").getBytes("iso-8859-1"), "utf-8");
        long resultCode = LoanTypeController.getInstance().addLoanFileToLoanType(LoanTypeController.getInstance().getLoanTypeByName(selectedLoanType), Integer.parseInt(request.getParameter("customerNumberField")),
                Integer.parseInt(request.getParameter("durationField")), Long.parseLong(request.getParameter("priceField")));
        System.out.println("loan file id =" + resultCode);
        if (resultCode == LoanUtil.NO_GRANT_FITS) {
            String message = "تسهیلات با چنین شرایطی موجود نیست";
            String errorMessage = new String(message.getBytes("utf-8"), "iso-8859-1");
            response.sendRedirect("/problemFoundServlet?errorMessage=" + errorMessage + "&parentURL=createLoanFile");
        } else {
            request.setAttribute("result","success");
            RequestDispatcher rd = request.getRequestDispatcher("/CreateLoanFile.jsp");
            rd.forward(request, response);
        }
    }
}
