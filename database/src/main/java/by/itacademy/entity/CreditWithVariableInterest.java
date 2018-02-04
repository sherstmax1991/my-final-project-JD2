package by.itacademy.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@DiscriminatorValue("VARIABLE")
public class CreditWithVariableInterest extends Credit {

    @Column(name = "variable_interest")
    private Double interestRateIncrement;

    public Double getRealInterestRate(Double baseInterestRate) {
        return baseInterestRate + interestRateIncrement;
    }
}
