package com.prog_avanzada.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@Table(name = "authors")
public class Author {

    @Id
    private Long id;

    private String name;

    @Version
    private Integer version;

    @ManyToMany(mappedBy = "authors")
    @ToString.Exclude
    private List<Book> books;
}