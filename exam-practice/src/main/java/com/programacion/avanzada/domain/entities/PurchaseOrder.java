package com.programacion.avanzada.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity(name = "purchase_orders")
@Table(name = "purchase_orders")
public class PurchaseOrder {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private BigDecimal price;

  @ToString.Exclude
  @ManyToOne
  @JoinColumn(name = "client_id")
  private Client client;

  @ToString.Exclude
  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;
}
