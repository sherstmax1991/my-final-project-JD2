package by.itacademy.servlet;

import by.itacademy.service.CreditApplicationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/credit_applications")
public class CreditApplicationsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("creditApplications", new CreditApplicationService().getAllCreditApplications());
        req.getRequestDispatcher("/WEB-INF/jsp/creditApplications.jsp")
                .forward(req, resp);
    }
}