package com.prog_avanzada.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "line_items")
@Data
public class LineItem {
    @Id
    private Long id;

    private Integer idx;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @ToString.Exclude
    private PurchaseOrder purchaseOrder;

    @ManyToOne
    @JoinColumn(name = "book_isbn")
    @ToString.Exclude
    private Book book;

}