package ua.ip.noteApp.account;

import jakarta.persistence.*;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ua.ip.noteApp.note.NoteDAO;
import ua.ip.noteApp.roles.RoleDAO;

import java.util.*;

@Entity
@Getter
@Setter
@Table(name = "users")
public class UserDAO {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "email", nullable = false, length = 60, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<NoteDAO> notes = new LinkedHashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role_relation",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleDAO> roles = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "friend",
            joinColumns = @JoinColumn(name = "friend_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<UserDAO> users = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "friend",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private Set<UserDAO> friends  = new HashSet<>();

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "friends_notes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_note_id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<NoteDAO> friendsNotes = new HashSet<>();
}
