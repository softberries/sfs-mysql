package com.softwarepassion.sfs.rest;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PageFactory {

    Pageable getPageableFromCriterias(DataTableCriterias criterias) {
        //todo: build sorting
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        return new PageRequest(criterias.getDraw(), criterias.getLength(), sort);
    }
}
