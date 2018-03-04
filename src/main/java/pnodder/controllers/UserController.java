package pnodder.controllers;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pnodder.repositories.UserRepository;

@Controller
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public String users(Model model) {
        if (SecurityUtils.getSubject().isAuthenticated()) {
            model.addAttribute("allUsers", userRepository.findAllWithRolesPermissions());
            return "users";
        } else {
            return "redirect:/booking";
        }
    }

}
