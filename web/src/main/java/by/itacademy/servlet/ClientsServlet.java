package by.itacademy.servlet;

import by.itacademy.service.ClientService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/clients")
public class ClientsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("clients", ClientService.getInstance().getAllClients());
        req.getRequestDispatcher("/WEB-INF/jsp/clients.jsp")
                .forward(req, resp);
    }
}