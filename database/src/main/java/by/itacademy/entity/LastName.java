package by.itacademy.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "last_names")
public class LastName extends BaseEntity {

    @Column(name = "male_equivalent", nullable = false)
    private String maleEquivalent;

    @Column(name = "female_equivalent", nullable = false)
    private String femaleEquivalent;
}
