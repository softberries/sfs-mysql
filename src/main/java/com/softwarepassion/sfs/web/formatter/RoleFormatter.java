package com.softwarepassion.sfs.web.formatter;

import com.softwarepassion.sfs.web.dto.RoleDTO;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class RoleFormatter implements Formatter<RoleDTO> {

    @Override
    public String print(RoleDTO roleDto, Locale locale) {
        return roleDto.getId();
    }

    @Override
    public RoleDTO parse(String id, Locale locale) throws ParseException {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(id);
        return roleDTO;
    }
}