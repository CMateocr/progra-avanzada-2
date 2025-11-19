package com.prog_avanzada.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private String isbn;
    private Double price;
    private String title;
    private Integer version;

}