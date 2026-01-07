package com.programacion.avanzada.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
// ! check how index works
// ! dejar la generacion por tabla e insertar para ver los sql
@Table(name = "authors", uniqueConstraints = { @UniqueConstraint(columnNames = {"name"}) })
@Entity
public class Author {
  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)

//  @SequenceGenerator(
//      name = "seqauthors",
//      sequenceName = "seq_authors",
//      allocationSize = 1000,
//      initialValue = 10000
//  )
//  @GeneratedValue(generator = "seqauthors")

  @TableGenerator(name = "gen-author", table = "ids_generados", pkColumnName = "tabla", valueColumnName = "sigid", pkColumnValue = "xxxxx")
  @GeneratedValue(generator = "gen-author")
  private Long id;

  @Column(length = 64, nullable = false)
  private String name;

  private Integer version;

  @Builder.Default
  @ToString.Exclude
  @ManyToMany(mappedBy = "authors")
  private List<Book> books = new ArrayList<>();
}
