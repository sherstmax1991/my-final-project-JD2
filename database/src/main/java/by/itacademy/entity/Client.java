package by.itacademy.entity;

import by.itacademy.dto.ClientDto;
import by.itacademy.entity.enums.ClientRating;
import by.itacademy.entity.enums.Gender;
import by.itacademy.entity.enums.MaritalStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Entity
@Embeddable
//@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clients")
@ToString(exclude = {"creditApplications"}, callSuper = true)
public class Client extends BaseEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "birthday", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "rating", nullable = false)
    @Enumerated(EnumType.STRING)
    private ClientRating rating;

    @Column(name = "marital_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;


    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "clients_roles",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ManyToMany
    private List<Role> roles;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "clients_children",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "child_id")
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Child> children;

    @OneToMany(mappedBy = "client", orphanRemoval = true)
    @JsonIgnore
    private List<CreditApplication> creditApplications;

    @Column(name = "if_real")
    private boolean ifReal;

    public Client() {
        this.roles = Collections.emptyList();
        this.children = Collections.emptyList();
        this.creditApplications = Collections.emptyList();
    }

    public Client(ClientDto clientDto) {
            this.firstName = clientDto.getFirstName();
            this.lastName = clientDto.getLastName();
            this.birthday = clientDto.getBirthday();
            this.gender = clientDto.getGender();
            this.rating = clientDto.getRating();
            this.maritalStatus = clientDto.getMaritalStatus();
            this.roles = Collections.singletonList(new Role(1L, "USER"));
            this.children = clientDto.getChildren();
    }
}
