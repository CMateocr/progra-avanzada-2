package com.prog_avanzada.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {

    private String bookIsbn;
    private Integer sold;
    private Integer supplied;
    private Integer version;

}