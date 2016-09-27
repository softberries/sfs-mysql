package com.softwarepassion.sfs.web;

import com.softwarepassion.sfs.model.User;
import com.softwarepassion.sfs.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/index")
    public String userIndex(HttpServletRequest request,
                            @SortDefault(value = "id", direction = Sort.Direction.DESC)
                            @PageableDefault(size = 1000) Pageable pageable) {
        User user = userRepository.findOne(1L);
        request.getSession().setAttribute("loggedInUser", user);
        return "user/index";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        User user = userRepository.findOne(id);
        model.addAttribute("userEdited", user);
        return "user/edit";
    }
}
