package com.softwarepassion.sfs.rest;


import com.softwarepassion.sfs.model.User;
import com.softwarepassion.sfs.repository.service.UserService;
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

    private final UserService userService;
    private final PageFactory pageFactory;

    @Autowired
    public UserResource(UserService userService, PageFactory pageFactory) {
        this.userService = userService;
        this.pageFactory = pageFactory;
    }

    @RequestMapping(method = RequestMethod.GET)
    DataTableWrapper<User> readBookmarks(@ModelAttribute DataTableCriterias criterias) {
        Pageable pageable = pageFactory.getPageableFromCriterias(criterias);
        Page<User> page = userService.searchByMultipleColumns(
                criterias.getSearch().get(DataTableCriterias.SearchCriterias.value),
                pageable);
        return new DataTableWrapper<User>(
                page.getContent(),
                criterias.getDraw(),
                userService.count(),
                page.getTotalElements());
    }
}
