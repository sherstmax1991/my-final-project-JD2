package by.itacademy.servlet;

import by.itacademy.dto.CreditApplicationSearchFormDto;
import by.itacademy.dto.CreditApplicationSearchResultMessageDto;
import by.itacademy.entity.CreditApplication;
import by.itacademy.entity.enums.ApplicationQuality;
import by.itacademy.entity.enums.ClientRating;
import by.itacademy.entity.enums.Gender;
import by.itacademy.entity.enums.MaritalStatus;
import by.itacademy.service.CreditApplicationService;
import by.itacademy.service.CreditService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.joining;

@WebServlet("/credit_applications")
public class CreditApplicationsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("credits", CreditService.getInstance().getCredits());
        req.setAttribute("clientRatings", ClientRating.values());
        req.setAttribute("gender", Gender.values());
        req.setAttribute("maritalStatus", MaritalStatus.values());
        req.setAttribute("applicationQuality", ApplicationQuality.values());
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/creditApplications.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        String jsonString = req.getReader().lines().collect(joining("\n"));
        CreditApplicationSearchFormDto form = gson.fromJson(jsonString, CreditApplicationSearchFormDto.class);

        List<CreditApplication> creditApplications = CreditApplicationService.getInstance().findByParameters(
                LocalDate.parse(form.getDateFrom()), LocalDate.parse(form.getDateTo()), form.getAgeFrom(),
                form.getAgeTo(), form.getChildrenFrom(), form.getChildrenTo(), form.getGender(),
                form.getMaritalStatus(), form.getClientRating(), form.getCreditId(), form.getIncomeFrom(),
                form.getIncomeTo(), form.getPledgeFrom(), form.getPledgeTo(), form.getSumFrom(), form.getSumTo(),
                form.getLoanPeriodFrom(), form.getLoanPeriodTo(), form.getApplicationQuality(),
                form.getScoringSystemResolution(), form.getPage(), form.getApplicationsPerPage());

        resp.setContentType("application/json");
        CreditApplicationSearchResultMessageDto creditApplicationSearchResultMessageDto =
                                    new CreditApplicationSearchResultMessageDto(creditApplications);
        String messageToJson = gson.toJson(creditApplicationSearchResultMessageDto);
        resp.getWriter().write(messageToJson);
    }
}