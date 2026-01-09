package com.programacion.avanzada.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "customers")
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String email;
  private String name;
  private Integer version;

  @Builder.Default
  @OneToMany(mappedBy = "customer")
  @ToString.Exclude
  private List<PurchaseOrder> purchaseOrders = new ArrayList<>();
}
