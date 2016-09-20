package com.softwarepassion.sfs.web;

import com.softwarepassion.sfs.model.forms.RegistrationForm;
import lombok.extern.slf4j.Slf4j;
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

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        RegistrationForm registration = new RegistrationForm();
        model.addAttribute("registrationForm", registration);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(@Valid RegistrationForm registrationForm, BindingResult result, Errors errors) {
        log.info("Registration form received: " + registrationForm);
        log.info("Registration errors received: " + errors);
        log.info("Registration br received: " + result);
        if (result.hasErrors()) {
            return new ModelAndView("register", "registrationForm", registrationForm);
        }
        return new ModelAndView("index");
    }

}
