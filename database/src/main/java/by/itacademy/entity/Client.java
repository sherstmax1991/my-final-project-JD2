package by.itacademy.entity;

import by.itacademy.entity.enums.ClientRating;
import by.itacademy.entity.enums.Gender;
import by.itacademy.entity.enums.MaritalStatus;
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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clients")
@ToString(exclude = {"children", "creditApplications"})
public class Client extends BaseEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "rating", nullable = false)
    @Enumerated(EnumType.STRING)
    private ClientRating rating;

    @Column(name = "marital_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    @ManyToMany
    @JoinTable(
            name = "clients_children",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "child_id")
    )
    private List<Child> children = new ArrayList<>();

    @OneToMany(mappedBy = "client", orphanRemoval = true)
    private List<CreditApplication> creditApplications = new ArrayList<>();
}
