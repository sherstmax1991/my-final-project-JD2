package by.itacademy.servlet;

import by.itacademy.service.CreditService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/credits")
public class CreditsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("credits", new CreditService().getFirstCredit());
        req.getRequestDispatcher("/WEB-INF/jsp/credits.jsp")
                .forward(req, resp);
    }
}