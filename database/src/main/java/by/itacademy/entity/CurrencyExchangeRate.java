package by.itacademy.entity;

import by.itacademy.dto.CurrencyExchangeRateEditResultDto;
import by.itacademy.entity.enums.Currency;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Version;

//import org.springframework.data.annotation.Version;

@Getter
@Setter
@Entity
@ToString(callSuper = true)
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exchangeRates")
public class CurrencyExchangeRate extends BaseEntity {

    @Version
    @JsonIgnore
    @Column(name = "version")
    private Long version;

    @Column(name = "currency", nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name = "buy", nullable = false)
    private Double buy;

    @Column(name = "sell", nullable = false)
    private Double sell;

    @Column(name = "basicAmount", nullable = false)
    private Integer basicAmount;

    public CurrencyExchangeRate(CurrencyExchangeRateEditResultDto dto) {
        this.version = dto.getVersion();
        super.setId(dto.getId());
        this.version = dto.getVersion();
        this.currency = dto.getCurrency();
        this.buy = dto.getBuy();
        this.sell = dto.getSell();
        this.basicAmount = dto.getBasicAmount();
    }
}
