package ua.ip.noteApp.signup;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.ip.noteApp.account.UserDTO;
import ua.ip.noteApp.account.UserService;
import ua.ip.noteApp.account.UserValidator;
import ua.ip.noteApp.roles.RoleService;
import ua.ip.noteApp.signin.SecurityService;

import java.util.Collections;

@Controller
@RequiredArgsConstructor
public class SignupController {
    private final UserService userService;
    private final RoleService roleService;
    private final SecurityService securityService;
    private final UserValidator userValidator;
    @GetMapping("/registration")
    public String registration(Model model) {

        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }

        model.addAttribute("userForm", new UserDTO());

        return "signup/registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") UserDTO userForm, BindingResult bindingResult,
                               HttpServletRequest request) {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "signup/registration";
        }
        userForm.setRoles(Collections.singletonList(roleService.getByName("ROLE_USER")));
        userService.save(userForm);
        securityService.autoLogin(userForm.getEmail(), userForm.getPasswordConfirm(), request);
        return "redirect:/";
    }
}
