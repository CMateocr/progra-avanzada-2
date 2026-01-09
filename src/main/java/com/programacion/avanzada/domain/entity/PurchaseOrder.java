package com.programacion.avanzada.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "purchase_orders")
public class PurchaseOrder {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "delivered_on")
  private LocalDateTime deliveredOn;

  @Column(name = "placed_on")
  private LocalDateTime placedOn;

  @Enumerated(EnumType.ORDINAL)
  private Status status;

  private Integer total;

  @Builder.Default
  @ToString.Exclude
  @OneToMany(mappedBy = "purchaseOrder")
  private List<LineItem> lineItems = new ArrayList<>();

  @ToString.Exclude
  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;
}
