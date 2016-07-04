package presentation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by m.farsiabi on 7/2/2016.
 */
public class ProblemFoundServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("errorMessage:" + request.getParameter("errorMessage"));
        request.setAttribute("errorMessage",new String(request.getParameter("errorMessage").getBytes("iso-8859-1"),"utf-8"));
        String parentURL = request.getParameter("parentURL");
        parentURL = parentURL.replace('$','&');
        request.setAttribute("parentURL",parentURL);
        System.out.println("parentURL:" + parentURL);
        RequestDispatcher rd = request.getRequestDispatcher("/ProblemFound.jsp");
        rd.forward(request, response);
    }
}
