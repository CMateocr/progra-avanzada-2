package com.programacion.avanzada.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "authors")
@Entity
public class Author {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private Integer version;

  @Builder.Default
  @ToString.Exclude
  @ManyToMany(mappedBy = "authors")
  private List<Book> books = new ArrayList<>();
}
