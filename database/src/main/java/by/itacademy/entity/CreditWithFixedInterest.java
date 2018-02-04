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
@DiscriminatorValue("FIXED")
public class CreditWithFixedInterest extends Credit {

    @Column(name = "fixed_interest")
    private Double interestRate;
}
