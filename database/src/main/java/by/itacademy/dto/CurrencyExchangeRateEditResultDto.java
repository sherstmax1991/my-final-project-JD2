package by.itacademy.dto;

import by.itacademy.entity.enums.Currency;
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
public class CurrencyExchangeRateEditResultDto {

    private Long id;
    private Long version;
    private Currency currency;
    private Double buy;
    private Double sell;
    private Integer basicAmount;
}
