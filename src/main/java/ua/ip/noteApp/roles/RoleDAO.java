package ua.ip.noteApp.roles;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ua.ip.noteApp.account.UserDAO;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "roles")
@Setter
@Getter
public class RoleDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    private Set<UserDAO> users = new LinkedHashSet<>();

}
