package com.softwarepassion.sfs.rest;


import com.softwarepassion.sfs.model.User;
import com.softwarepassion.sfs.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserResource {

    private final UserRepository userRepository;
    private final PageFactory pageFactory;

    @Autowired
    public UserResource(UserRepository userRepository, PageFactory pageFactory) {
        this.userRepository = userRepository;
        this.pageFactory = pageFactory;
    }

    @RequestMapping(method = RequestMethod.GET)
    DataTableWrapper<User> readBookmarks(@ModelAttribute DataTableCriterias criterias) {
        log.info("Criterias: " + criterias);
        Pageable pageable = pageFactory.getPageableFromCriterias(criterias);
        //todo: build searching
        Page<User> page = userRepository.findAll(pageable);
        long recordsFiltered = 1000; //after searching
        return new DataTableWrapper<User>(
            page.getContent(),
            criterias.getDraw(),
            userRepository.count(),
            recordsFiltered);
    }
}
