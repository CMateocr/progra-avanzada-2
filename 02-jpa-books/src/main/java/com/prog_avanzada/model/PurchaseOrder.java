package com.prog_avanzada.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "purchase_orders")
public class PurchaseOrder {
    enum Status {
        PROCESSED,
        NEW,
        UNPROCCESSED,
        OLD
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "placed_on")
    private LocalDateTime placedOn;

    @Column(name = "delivered_on")
    private LocalDateTime deliveredOn;

    private Integer total;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @ToString.Exclude
    private Customer customer;

    @OneToMany(mappedBy = "purchaseOrder")
    @ToString.Exclude
    private List<LineItem> lineItems;

}