package ua.ip.noteApp.account;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.ip.noteApp.EntityMapper;
import ua.ip.noteApp.exception.NotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EntityMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UserDTO save(UserDTO usersDto) {
        usersDto.setPassword(passwordEncoder.encode(usersDto.getPassword()));
        UserDAO savedUser = userRepository.save(mapper.userToDao(usersDto));
        return mapper.userToDTO(savedUser);
    }

    public List<UserDTO> listAll() {
        return userRepository.findAll()
                .stream()
                .map(mapper::userToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getById(UUID id) {
        return mapper.userToDTO(userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id " + id + " doesn't exist")));
    }

    public void deleteById(UUID id) {
        getById(id);
        userRepository.deleteById(id);
    }


    public List<UserDTO> findByName(String query) {
        return userRepository.findByEmail(query).stream()
                .map(mapper::userToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getAuthorizedUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO user = userRepository.findByEmail(userDetails.getUsername()).map(mapper::userToDTO)
                .orElseThrow(() -> new NotFoundException("Error with getting autorized user"));
        return user;
    }
}
