package pnodder.controllers;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pnodder.mappers.UserMapper;

@Controller
public class UserController {

    private UserMapper userMapper;

    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @GetMapping("/users")
    public String users(Model model) {
        if (SecurityUtils.getSubject().isAuthenticated()) {
            model.addAttribute("allUsers", userMapper.findAllWithRolesPermissions());
            return "users";
        } else {
            return "redirect:/booking";
        }
    }

}
