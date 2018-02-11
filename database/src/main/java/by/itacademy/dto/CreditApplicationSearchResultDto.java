package by.itacademy.dto;

import by.itacademy.entity.CreditApplication;
import by.itacademy.entity.enums.ApplicationQuality;
import by.itacademy.entity.enums.ClientRating;
import by.itacademy.entity.enums.Gender;
import by.itacademy.entity.enums.MaritalStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class CreditApplicationSearchResultDto implements Serializable {

    private static final long serialVersionUID = -2951976345741419035L;

    private LocalDate date;
    private LocalDate birthday;
    private Integer children;
    private Gender gender;
    private MaritalStatus maritalStatus;
    private ClientRating clientRating;
    private String creditTitle;
    private Integer income;
    private Integer pledge;
    private Integer sum;
    private Integer loanPeriod;
    private ApplicationQuality applicationQuality;
    private ApplicationQuality scoringSystemResolution;

    public CreditApplicationSearchResultDto(CreditApplication creditApplication) {
        this.date = creditApplication.getApplicationDate();
        this.birthday = creditApplication.getClient().getBirthday();
        this.children = creditApplication.getClient().getChildren().size();
        this.gender = creditApplication.getClient().getGender();
        this.maritalStatus = creditApplication.getClient().getMaritalStatus();
        this.clientRating = creditApplication.getClient().getRating();
        this.creditTitle = creditApplication.getCredit().getTitle();
        this.income = creditApplication.getIncome();
        this.pledge = creditApplication.getPledge();
        this.sum = creditApplication.getSum();
        this.loanPeriod = creditApplication.getLoanPeriod();
        this.applicationQuality = creditApplication.getApplicationQuality();
        this.scoringSystemResolution = creditApplication.getScoringSystemResolution();
    }
}
