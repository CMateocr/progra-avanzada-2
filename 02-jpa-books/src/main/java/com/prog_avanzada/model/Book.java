package com.prog_avanzada.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "books")
public class Book {

    @Id
    @Column(length = 255)
    private String isbn;

    private String title;

    private BigDecimal price;

    @Version
    private Integer version;

    @ManyToMany
    @ToString.Exclude
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "books_isbn"),
            inverseJoinColumns = @JoinColumn(name = "authors_id")
    )
    private List<Author> authors;

    @OneToOne(mappedBy = "book")
    @ToString.Exclude
    private Inventory inventory;
}