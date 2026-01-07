package com.programacion.avanzada.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "books")
@Entity
public class Book {
  @Id
  @Column(name = "isbn", length = 16)
  private String isbn;
  private Integer version;
  @Column(length = 128)
  private String title;
  private BigDecimal price;

  @Builder.Default
  @ToString.Exclude
  @ManyToMany
  @JoinTable(
    name = "book_authors",
    joinColumns = @JoinColumn(name = "book_isbn"),
    inverseJoinColumns = @JoinColumn(name = "author_id")
  )
  private List<Author> authors = new ArrayList<>();

  @ToString.Exclude
  @OneToOne(mappedBy = "book")
  private Inventory inventory;
}