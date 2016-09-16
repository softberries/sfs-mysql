package com.softwarepassion.sfs.rest;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static org.hibernate.internal.util.StringHelper.isNotEmpty;

@Service
class PageFactory {

    Pageable getPageableFromCriterias(DataTableCriterias criterias) {
        String sortColumnNumber = criterias.getOrder().get(0).get(DataTableCriterias.OrderCriterias.column);
        String sortColumnDirection = criterias.getOrder().get(0).get(DataTableCriterias.OrderCriterias.dir);
        String sortColumnName = criterias.getColumns().get(Integer.parseInt(sortColumnNumber)).get(DataTableCriterias.ColumnCriterias.data);
        int pageNumber = calculatePageNumber(criterias);
        if (isNotEmpty(sortColumnName) && isNotEmpty(sortColumnDirection)) {
            Sort sort = new Sort(new Sort.Order("asc".equals(sortColumnDirection) ? Sort.Direction.ASC : Sort.Direction.DESC, sortColumnName));
            return new PageRequest(pageNumber, criterias.getLength(), sort);
        } else {
            return new PageRequest(pageNumber, criterias.getLength());
        }
    }

    private int calculatePageNumber(DataTableCriterias criterias) {
        if (criterias.getStart() == 0) {
            return 0;
        } else {
            return criterias.getStart() / criterias.getLength();
        }
    }
}