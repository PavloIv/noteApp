package ua.ip.noteApp.friend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.ip.noteApp.account.UserDTO;
import ua.ip.noteApp.account.UserService;

import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/friendsnotes")
public class FriendsNotesController {
    private final UserService userService;

    @GetMapping
    public ModelAndView findAllFriendsNotes(){
        ModelAndView result = new ModelAndView("friendsnotes/friendsNotes");
        UserDTO authUser = userService.getAuthorizedUser();
        result.addObject("notes",userService.allFriendsNotes(authUser));
        return result;
    }

    @GetMapping("/add/{id}")
    public String addFriendNote(@PathVariable("id") UUID id) {
        userService.addFriendNote(userService.getAuthorizedUser(),id);
        return "redirect:/friendsnotes";
    }

    @GetMapping("/delete/{id}")
    public String deleteFriendNote(@PathVariable("id") UUID id) {
        userService.deleteFriendNote(userService.getAuthorizedUser(),id);
        return "redirect:/friendsnotes";
    }
}

