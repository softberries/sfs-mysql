package com.softwarepassion.sfs.web;

import com.softwarepassion.sfs.model.User;
import com.softwarepassion.sfs.util.GravatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class MainController {

    private final GravatarService gravatarService;

    @Autowired
    public MainController(GravatarService gravatarService) {
        this.gravatarService = gravatarService;
    }

    @RequestMapping("/")
    public String root() {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/user/index")
    public String userIndex(Model model) {
        model.addAttribute("user", new User(gravatarService.getGravatarURL("krzysztof.grajek@googlemail.com", Optional.of(32))));
        return "user/index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
}