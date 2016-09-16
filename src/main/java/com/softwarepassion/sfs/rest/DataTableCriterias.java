package com.softwarepassion.sfs.rest;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
class DataTableCriterias {
    private int draw;
    private int start;
    private int length;

    private Map<SearchCriterias, String> search;

    private List<Map<ColumnCriterias, String>> columns;

    private List<Map<OrderCriterias, String>> order;

    public enum SearchCriterias {
        value,
        regex
    }

    public enum OrderCriterias {
        column,
        dir
    }

    public enum ColumnCriterias {
        data,
        name,
        searchable,
        orderable,
        searchValue,
        searchRegex
    }
}