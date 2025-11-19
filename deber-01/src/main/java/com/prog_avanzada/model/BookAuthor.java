package com.prog_avanzada.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookAuthor {

    private String bookIsbn;
    private Long authorId;

}