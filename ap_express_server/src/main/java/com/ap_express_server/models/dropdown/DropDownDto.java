package com.ap_express_server.models.dropdown;

import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;

@Data
public class DropDownDto {
    private Integer id;
    private String name;

    public DropDownDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }


}
