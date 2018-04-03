package by.itacademy.dto;

import by.itacademy.entity.CreditApplication;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClientCreditInfoDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate applicationDate;
    private String creditTitle;
    private Integer sum;
    private Integer pledge;
    private LocalDate lastPayment;
    private boolean ifActive;

    public ClientCreditInfoDto(CreditApplication creditApplication) {
        this.applicationDate = creditApplication.getApplicationDate();
        this.creditTitle = creditApplication.getCredit().getTitle();
        this.sum = creditApplication.getSum();
        this.pledge = creditApplication.getPledge();
        this.lastPayment = creditApplication.getApplicationDate().plusMonths(creditApplication.getLoanPeriod());
        this.ifActive = this.lastPayment.isAfter(LocalDate.now());
    }
}
