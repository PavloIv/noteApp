package ua.ip.noteApp.account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.ip.noteApp.roles.RoleService;
import ua.ip.noteApp.signup.UserValidator;

import java.util.Collections;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/users")
@Controller
@Slf4j
public class UserController {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final RoleService roleService;
    private final UserValidator userValidator;

    @GetMapping("/create")
    public String saveForm() {
        return "user/create";
    }

    @PostMapping("/create")
    public String saveUser(@Validated @ModelAttribute(name = "userDto") UserDTO userDTO, BindingResult bindingResult) {
        log.info("Handling save users: " + userDTO);
        userValidator.validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return "user/create";
        }
        userDTO.setRoles(Collections.singletonList(roleService.getByName("ROLE_USER")));
        userService.save(userDTO);
        return "users/findAll";
    }

    @GetMapping
    public ModelAndView findAllUsers(@RequestParam(value = "id", required = false, defaultValue = "") String id) {
        log.info("Handling find all users request");
        ModelAndView result = new ModelAndView("user/findAll");

        if (Objects.nonNull(id) & !id.isEmpty() & !id.isBlank()) {
            result.addObject("users", userService.getById(UUID.fromString(id)));
        } else {
            result.addObject("users", userService.listAll());
        }

        result.addObject("users", Collections.EMPTY_LIST);
        return result;
    }

    @GetMapping("/{id}")
    public ModelAndView updateUserById(@PathVariable("id") UUID id) {
        ModelAndView result = new ModelAndView("user/editForm");
        UserDTO usersDto = userService.getById(id);
        result.addObject("user", usersDto);
        return result;
    }

    @DeleteMapping("/{id}")
    public String deleteUserByIdForm(@PathVariable("id") UUID id) {
        userService.deleteById(id);
        return "user/findAll";
    }
}
