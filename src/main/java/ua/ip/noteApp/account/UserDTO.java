package ua.ip.noteApp.account;

import lombok.Data;
import ua.ip.noteApp.note.NoteDAO;
import ua.ip.noteApp.roles.RoleDAO;

import java.util.*;

@Data
public class UserDTO {

    private UUID id;
    private String email;
    private String password;
    private String passwordConfirm;
    private Set<NoteDAO> notes = new LinkedHashSet<>();
    private List<RoleDAO> roles = new ArrayList<>();
}
