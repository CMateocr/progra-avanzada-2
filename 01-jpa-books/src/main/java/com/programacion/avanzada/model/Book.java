package com.programacion.avanzada.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Entity()
@Table(name = "book")
public class Book {
  @Id
  private String isbn;
  private Double price;
  private String title;
  private Integer version;

  @ManyToMany()
//  @JoinTable(
//    name = "book_author",
//    joinColumns = @JoinColumn(name = "books_isbn"),
//    inverseJoinColumns = @JoinColumn(name = "authors_id")
//  )
  private List<Author> authors;
}