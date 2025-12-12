package com.prog_avanzada.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @Version
    private Integer version;

    @OneToMany(mappedBy = "customer")
    @ToString.Exclude
    private List<PurchaseOrder> purchaseOrders = new ArrayList<>();

}