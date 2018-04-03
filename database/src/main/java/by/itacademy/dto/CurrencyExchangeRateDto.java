package by.itacademy.dto;

import by.itacademy.entity.CurrencyExchangeRate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.text.DecimalFormat;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CurrencyExchangeRateDto {

    private static final DecimalFormat FORMATTER = new DecimalFormat(".0000");

    private String title;
    private String buy;
    private String sell;

    public CurrencyExchangeRateDto(CurrencyExchangeRate course) {
        int amount = course.getBasicAmount();
        this.title = amount == 1 ? course.getCurrency().toString() : amount + " " + course.getCurrency().toString();
        this.buy = FORMATTER.format(course.getBuy() * amount);
        this.sell = FORMATTER.format(course.getSell() * amount);
    }
}
