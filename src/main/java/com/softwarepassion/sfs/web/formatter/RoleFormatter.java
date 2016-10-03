package com.softwarepassion.sfs.web.formatter;

import com.softwarepassion.sfs.web.dto.RoleDTO;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class RoleFormatter implements Formatter<RoleDTO> {

    @Override
    public String print(RoleDTO role, Locale locale) {
        return role.getName();
    }

    @Override
    public RoleDTO parse(String name, Locale locale) throws ParseException {
        RoleDTO role = new RoleDTO(name, false);
        return role;
    }
}