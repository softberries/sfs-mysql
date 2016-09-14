package com.softwarepassion.sfs.web;

import com.softwarepassion.sfs.model.User;
import com.softwarepassion.sfs.repository.UserRepository;
import com.softwarepassion.sfs.util.GravatarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Slf4j
public class MainController {

    private final GravatarService gravatarService;
    private final UserRepository userRepository;

    @Autowired
    public MainController(GravatarService gravatarService, UserRepository userRepository) {
        this.gravatarService = gravatarService;
        this.userRepository = userRepository;
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
        User user = userRepository.findOne(1L);
        log.info("user found: " + user);
        model.addAttribute("user", user);
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