package ua.ip.noteApp;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.ip.noteApp.account.UserDAO;
import ua.ip.noteApp.account.UserDTO;
import ua.ip.noteApp.note.NoteDAO;
import ua.ip.noteApp.note.NoteDTO;

import java.time.LocalDate;

@Mapper(componentModel = "Spring", imports = {LocalDate.class})
public interface EntityMapper {

    @Mapping(target = "passwordConfirm", ignore = true)
    UserDTO userToDTO(UserDAO userDAO);
    UserDAO userToDAO(UserDTO userDTO);


    NoteDTO noteToDTO(NoteDAO dao);
    NoteDAO noteToDAO(NoteDTO dto);
}