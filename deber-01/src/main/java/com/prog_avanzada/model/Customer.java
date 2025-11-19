package com.prog_avanzada.model;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Customer {

    private Long id;
    private String email;
    private String name;
    private Integer version;

}