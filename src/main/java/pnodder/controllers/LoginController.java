package pnodder.controllers;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pnodder.model.User;

@Controller("/login")
public class LoginController {

    final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping
    public String doLogin(User user, Model model) {
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        token.setRememberMe(true);
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(token);
        } catch (AuthenticationException e) {
            logger.info("Authentication failed: ", e);
            model.addAttribute("loginError", "incorrect username or password");
            return "login";
        }
        return "redirect:/booking";
    }

}
