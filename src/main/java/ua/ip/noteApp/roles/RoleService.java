package ua.ip.noteApp.roles;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.ip.noteApp.exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleDAO getByName(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> new NotFoundException("Role with name " +
                name + " doesn't exist"));
    }
}