package com.softwarepassion.sfs.web;

import com.softwarepassion.sfs.forms.RegistrationForm;
import com.softwarepassion.sfs.repository.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@Slf4j
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        RegistrationForm registration = new RegistrationForm();
        model.addAttribute("registrationForm", registration);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(@Valid RegistrationForm registrationForm, BindingResult result, Errors errors) {
        if (userService.isEmailDuplicated(registrationForm.getEmail())) {
            result.rejectValue("email", "message.registrationError");
        }
        if (result.hasErrors()) {
            return new ModelAndView("register", "registrationForm", registrationForm);
        } else {
            userService.register(registrationForm);
        }
        return new ModelAndView("redirect:/login");
    }

}
