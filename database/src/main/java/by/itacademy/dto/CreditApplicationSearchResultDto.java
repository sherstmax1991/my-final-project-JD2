package by.itacademy.dto;

import by.itacademy.entity.CreditApplication;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@ToString
public class CreditApplicationSearchResultDto {

//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String date;
    private Long age;
    private String gender;
    private String maritalStatus;
    private String clientRating;
    private Integer children;
    private String creditTitle;
    private Integer income;
    private Integer pledge;
    private Integer sum;
    private Integer loanPeriod;
    private String applicationQuality;
    private String scoringSystemResolution;

    public CreditApplicationSearchResultDto(CreditApplication creditApplication) {
        this.date = creditApplication.getApplicationDate().toString();
        this.age = ChronoUnit.YEARS.between(creditApplication.getClient().getBirthday(), LocalDate.now());
        this.children = creditApplication.getClient().getChildren().size();
        this.gender = creditApplication.getClient().getGender().toString();
        this.maritalStatus = creditApplication.getClient().getMaritalStatus().toString();
        this.clientRating = creditApplication.getClient().getRating().toString();
        this.creditTitle = creditApplication.getCredit().getTitle();
        this.income = creditApplication.getIncome();
        this.pledge = creditApplication.getPledge();
        this.sum = creditApplication.getSum();
        this.loanPeriod = creditApplication.getLoanPeriod();
        this.applicationQuality = creditApplication.getApplicationQuality().toString();
        this.scoringSystemResolution = creditApplication.getScoringSystemResolution().toString();
    }
}