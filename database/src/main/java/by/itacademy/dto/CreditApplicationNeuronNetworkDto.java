package by.itacademy.dto;

import by.itacademy.entity.CreditApplication;
import by.itacademy.entity.enums.ApplicationQuality;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditApplicationNeuronNetworkDto {

    private static final Map<String, Integer> GENDER_MAP = new HashMap<>();
    private static final Map<String, Integer> CLIENT_RATING_MAP = new HashMap<>();
    private static final Map<String, Integer> MARITAL_STATUS_MAP = new HashMap<>();
    private static final Map<String, Integer> APPLICATION_QUALITY_MAP = new HashMap<>();
    private static final int END_OF_CHILDHOOD = 18;

    private Integer clientAge;
    private Integer gender;
    private Integer children;
    private Integer clientRating;
    private Integer maritalStatus;
    private Double loanPayments;
    private Integer loanPeriod;
    private Integer guarantors;
    private Double coverageByPledge;
    private Integer ifGoodApplication;
    private Integer ifBadApplication;

    static {
        GENDER_MAP.put("MALE", 1);
        GENDER_MAP.put("FEMALE", 0);
        CLIENT_RATING_MAP.put("BAD", 1);
        CLIENT_RATING_MAP.put("PROBLEM", 2);
        CLIENT_RATING_MAP.put("NORMAL", 3);
        CLIENT_RATING_MAP.put("GOOD", 4);
        CLIENT_RATING_MAP.put("VIP", 5);
        MARITAL_STATUS_MAP.put("SINGLE", 1);
        MARITAL_STATUS_MAP.put("DIVORCED", 2);
        MARITAL_STATUS_MAP.put("MARRIED", 3);
        APPLICATION_QUALITY_MAP.put("GOOD", 1);
        APPLICATION_QUALITY_MAP.put("BAD", 0);
    }

    public CreditApplicationNeuronNetworkDto(CreditApplication application) {
        clientAge = (int) ChronoUnit.YEARS.between(application.getClient().getBirthday(),
                                                    application.getApplicationDate());
        gender = GENDER_MAP.get(application.getClient().getGender().toString());
        children = (int) application.getClient().getChildren().stream()
                .filter(children -> (int) ChronoUnit.YEARS
                                .between(children.getBirthday(), application.getApplicationDate()) < END_OF_CHILDHOOD)
                .count();
        clientRating = CLIENT_RATING_MAP.get(application.getClient().getRating().toString());
        maritalStatus = MARITAL_STATUS_MAP.get(application.getClient().getMaritalStatus().toString());
        loanPayments = annuityMonthPayment(application.getSum(), application.getLoanPeriod());
        loanPeriod = application.getLoanPeriod();
        guarantors = application.getCredit().getGuarantors();
        coverageByPledge = (double) Math.ceil(100 * application.getPledge() / application.getSum()) / 100;
        if (application.getApplicationQuality() != null) {
            ifGoodApplication = application.getApplicationQuality().equals(ApplicationQuality.GOOD) ? 1 : 0;
            ifBadApplication = application.getApplicationQuality().equals(ApplicationQuality.GOOD) ? 0 : 1;
        }
    }

//    private Double annuityMonthPaymentWithPercentage(int amountOfCredit, int loanPeriod, double annualInterestRate) {
//        double monthInterestRate = pow((annualInterestRate / 100 + 1), (double) 1/12) - 1;
//        double monthPayment = amountOfCredit * monthInterestRate * pow(1 + monthInterestRate, loanPeriod)/
//                                (pow((1 + monthInterestRate), loanPeriod) - 1);
//        monthPayment = Math.ceil(100 * monthPayment) / 100;
//        return monthPayment;
//    }

    private Double annuityMonthPayment(int amountOfCredit, int loanPeriod) {
        double monthPayment =  amountOfCredit / loanPeriod;
        monthPayment = Math.ceil(100 * monthPayment) / 100;
        return monthPayment;
    }

    @Override
    public String toString() {
        return clientAge + " " + gender + " " + children + " " + clientRating + " " + maritalStatus + " "
                + loanPayments + " " + loanPeriod + " " + guarantors + " " + coverageByPledge + " " + ifGoodApplication
                + " " + ifBadApplication;
    }
}