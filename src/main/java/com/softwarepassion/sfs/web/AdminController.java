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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    private final UserRepository userRepository;

    @Autowired
    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/index")
    public String adminIndex(HttpServletRequest request,
                             @SortDefault(value = "id", direction = Sort.Direction.DESC)
                             @PageableDefault(size = 1000) Pageable pageable) {
        User user = userRepository.findOne(1L);
        request.getSession().setAttribute("loggedInUser", user);
        return "admin/index";
    }

}
