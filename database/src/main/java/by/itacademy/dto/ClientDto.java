package by.itacademy.dto;

import by.itacademy.entity.Child;
import by.itacademy.entity.Client;
import by.itacademy.entity.enums.ClientRating;
import by.itacademy.entity.enums.Gender;
import by.itacademy.entity.enums.MaritalStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ClientDto {

    private String firstName;
    private String lastName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private Gender gender;
    private ClientRating rating;
    private MaritalStatus maritalStatus;
    private List<String> roles = new ArrayList<>();
    private List<Child> children = new ArrayList<>();

    public ClientDto(Client client) {
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.birthday = client.getBirthday();
        this.gender = client.getGender();
        this.rating = client.getRating();
        this.maritalStatus = client.getMaritalStatus();
        this.children = client.getChildren();
    }
}
