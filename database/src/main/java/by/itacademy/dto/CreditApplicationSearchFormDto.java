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

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreditApplicationSearchFormDto {

    private String dateFrom;
    private String dateTo;
    private Integer ageFrom;
    private Integer ageTo;
    private Integer childrenFrom;
    private Integer childrenTo;
    private Gender gender;
    private MaritalStatus maritalStatus;
    private ClientRating clientRating;
    private Long creditId;
    private Integer incomeFrom;
    private Integer incomeTo;
    private Integer pledgeFrom;
    private Integer pledgeTo;
    private Integer sumFrom;
    private Integer sumTo;
    private Integer loanPeriodFrom;
    private Integer loanPeriodTo;
    private ApplicationQuality applicationQuality;
    private ApplicationQuality scoringSystemResolution;
    private Integer page;
    private Integer applicationsPerPage;
}



