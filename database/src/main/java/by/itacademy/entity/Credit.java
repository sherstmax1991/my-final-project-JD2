package by.itacademy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "credits")
@ToString(exclude = "creditApplications", callSuper = true)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "interest_type")
public abstract class Credit extends BaseEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "guarantors", nullable = false)
    private Integer guarantors;

    @OneToMany(mappedBy = "credit", orphanRemoval = true)
    @JsonIgnore
    private List<CreditApplication> creditApplications = new ArrayList<>();
}
