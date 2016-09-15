package com.softwarepassion.sfs.rest;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
class DataTableWrapper<T> {

    private List<T> data;
    private long draw;
    private long recordsTotal;
    private long recordsFiltered;

}
