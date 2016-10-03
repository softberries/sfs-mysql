package com.softwarepassion.sfs.web;

import com.softwarepassion.sfs.forms.RegistrationForm;
import com.softwarepassion.sfs.model.Role;
import com.softwarepassion.sfs.model.User;
import com.softwarepassion.sfs.repository.RoleRepository;
import com.softwarepassion.sfs.repository.UserRepository;
import com.softwarepassion.sfs.repository.service.UserService;
import com.softwarepassion.sfs.web.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository,
                          RoleRepository roleRepository,
                          UserService userService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    @RequestMapping("/index")
    public String userIndex(HttpServletRequest request,
                            @SortDefault(value = "id", direction = Sort.Direction.DESC)
                            @PageableDefault(size = 1000) Pageable pageable) {
        return "user/index";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        User user = userRepository.findOne(id);
        List<Role> allRoles = roleRepository.findAll();
        model.addAttribute("userEdited", UserDTO.fromUser(user, allRoles));
        return "user/edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView updateUser(@Valid UserDTO userDTO, BindingResult result, Errors errors) {
        log.info("###### USER DTO: " + userDTO);
        if (userService.isEmailDuplicated(userDTO.getEmail())) {
            result.rejectValue("email", "message.registrationError");
        }
        if (result.hasErrors()) {
            return new ModelAndView("user/edit", "userEdited", userDTO);
        } else {
            userService.updateUser(userDTO);
        }
        return new ModelAndView("redirect:/admin/index");
    }
}
