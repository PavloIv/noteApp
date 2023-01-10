package ua.ip.noteApp.account;


import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UpdateInfo {
    private final UserService userService;
    private final UserValidator userValidator;


    @GetMapping("/settings/change")
    public String changePassword(Model model) {

        model.addAttribute("changePasswordForm", new ChangePasswordForm());
        return "account/change";
    }

    @PostMapping("/settings/change")
    public String changePassword(@ModelAttribute("changePasswordForm") ChangePasswordForm changePasswordForm,
                                 BindingResult bindingResult) {


        if (!userService.checkPassword(changePasswordForm.getOldPassword())){
            bindingResult.rejectValue("oldPassword", "error.oldPassword", "Wrong password!");
            return "account/change";
        }

        UserDTO user = userService.getAuthorizedUser();
        user.setPassword(changePasswordForm.getPassword());
        user.setPasswordConfirm(changePasswordForm.getPasswordConfirm());

        userValidator.validatePassword(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "account/change";
        }

        userService.save(user);
        return "redirect:/";

    }
}