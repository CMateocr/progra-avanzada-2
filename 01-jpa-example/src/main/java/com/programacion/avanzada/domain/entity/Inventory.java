package com.programacion.avanzada.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
