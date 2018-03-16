package by.itacademy.dto;

import by.itacademy.entity.enums.ApplicationQuality;
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

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreditApplicationSearchFormDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateTo;

    private Integer ageFrom;
    private Integer ageTo;
    private Integer childrenFrom;
    private Integer childrenTo;
    private List<MaritalStatus> maritalStatus;
    private List<Gender> gender;
    private List<ClientRating> clientRating;
    private List<Long> creditId;
    private Integer incomeFrom;
    private Integer incomeTo;
    private Integer pledgeFrom;
    private Integer pledgeTo;
    private Integer sumFrom;
    private Integer sumTo;
    private Integer loanPeriodFrom;
    private Integer loanPeriodTo;
    private List<ApplicationQuality> applicationQuality;
    private List<ApplicationQuality> scoringSystemResolution;
    private Integer page;
    private Integer applicationsPerPage;
}