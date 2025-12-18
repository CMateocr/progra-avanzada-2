package com.programacion.avanzada.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "line_items")
@Entity
@IdClass(LineItemId.class)
public class LineItem {
  @Id
  private Long idx;

  private Integer quantity;

  @ToString.Exclude
  @Id
  @ManyToOne
  @JoinColumn(name = "order_id")
  private PurchaseOrder purchaseOrder;

  @ToString.Exclude
  @ManyToOne
  @JoinColumn(name = "book_isbn")
  private Book book;
}
