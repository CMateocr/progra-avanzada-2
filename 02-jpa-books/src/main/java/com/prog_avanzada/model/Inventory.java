package com.prog_avanzada.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @Column(name = "book_isbn")
    private String bookIsbn;

    private Integer supplied;

    private Integer sold;

    @Version
    private Integer version;

    @OneToOne
    @JoinColumn(name = "book_isbn")
    @ToString.Exclude
    private Book book;

}