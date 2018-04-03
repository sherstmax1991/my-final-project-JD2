package by.itacademy.dto;

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
public class CreditApplicationDto {

    private Long creditId;
    private Integer loanPeriod;
    private Integer income;
    private Integer pledge;
    private Integer sum;
}
