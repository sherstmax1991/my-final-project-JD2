package by.itacademy.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "credits")
public class Credit extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "guarantors")
    private Integer guarantors;

    @Column(name = "interest_rate")
    private Double interestRate;

    @OneToMany(mappedBy = "credit", orphanRemoval = true)
    private List<CreditApplication> creditApplications = new ArrayList<>();
}
