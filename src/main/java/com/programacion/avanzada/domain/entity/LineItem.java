package com.programacion.avanzada.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "line_items")
@Entity
public class LineItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Integer quantity;

  @ToString.Exclude
  @ManyToOne
  @JoinColumn(name = "order_id")
  private PurchaseOrder purchaseOrder;

  @ToString.Exclude
  @ManyToOne
  @JoinColumn(name = "book_isbn")
  private Book book;
}
