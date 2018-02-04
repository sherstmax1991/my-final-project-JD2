package by.itacademy.entity;

import by.itacademy.entity.enums.ApplicationQuality;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(name = "applications")
public class CreditApplication extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "credit_id")
    private Credit credit;

    @Column(name = "date", nullable = false)
    private LocalDate applicationDate;

    @Column(name = "income", nullable = false)
    private Integer income;

    @Column(name = "pledge", nullable = false)
    private Integer pledge;

    @Column(name = "sum", nullable = false)
    private Integer sum;

    @Column(name = "loan_period", nullable = false)
    private Integer loanPeriod;

    @Column(name = "quality", nullable = false)
    @Enumerated(EnumType.STRING)
    private ApplicationQuality applicationQuality;

    @Column(name = "scoring_resolution", nullable = false)
    @Enumerated(EnumType.STRING)
    private ApplicationQuality scoringSystemResolution;
}