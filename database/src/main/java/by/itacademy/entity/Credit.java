package by.itacademy.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GenerationType;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(name = "credits")
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private Integer guarantors;

    @Column(name = "interest_rate")
    private Double interestRate;

    public Credit(String title, Integer guarantors, Double interestRate) {
        this.title = title;
        this.guarantors = guarantors;
        this.interestRate = interestRate;
    }
}
