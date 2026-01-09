package com.programacion.avanzada.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table
public class Inventory {
  @Id
  @Column(name = "book_isbn")
  private String bookIsbn;

  private Integer sold;
  private Integer supplied;
  private Integer version;

  @ToString.Exclude
  @OneToOne
  @MapsId
  @JoinColumn(name = "book_isbn")
  private Book book;
}
