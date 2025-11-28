package com.programacion.avanzada.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "books")
@Builder
@Getter
@Entity()
@Table(name = "author")
public class Author {
  @Id
  private Long id;
  private String name;
  private Integer version;

  @ManyToMany(mappedBy = "authors")
  private List<Book> books;
}