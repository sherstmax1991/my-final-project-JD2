package by.itacademy.dto;

import by.itacademy.entity.enums.ClientRating;
import by.itacademy.entity.enums.Gender;
import by.itacademy.entity.enums.MaritalStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreditApplicationGeneratorDto {

    private int amountOfClients;
    private int ageFrom;
    private int ageTo;
    private int childrenFrom;
    private int childrenTo;
    private List<Gender> gender;
    private List<ClientRating> clientRating;
    private List<MaritalStatus> maritalStatus;
    private List<Long> creditId;
    private int amountOfApplications;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateTo;
    private int incomeFrom;
    private int incomeTo;
    private int pledgeFrom;
    private int pledgeTo;
    private int sumFrom;
    private int sumTo;
    private int loanPeriodFrom;
    private int loanPeriodTo;
    private int goodApplicationPercent;
}
