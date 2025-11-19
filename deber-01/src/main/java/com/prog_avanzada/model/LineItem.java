package com.prog_avanzada.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineItem {

    private Long orderId;
    private Integer idx;
    private Integer quantity;
    private String bookIsbn;

}