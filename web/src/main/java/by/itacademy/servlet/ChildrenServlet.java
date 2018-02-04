package by.itacademy.servlet;

import by.itacademy.service.ChildService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/children")
public class ChildrenServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("children", new ChildService().getChildren());
        req.getRequestDispatcher("/WEB-INF/jsp/children.jsp")
                .forward(req, resp);
    }
}