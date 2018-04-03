package by.itacademy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@ToString(exclude = "users")
@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    public Role(Long id, String name) {
        super.setId(id);
        this.name = name;
    }

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<Client> users = new ArrayList<>();
}
