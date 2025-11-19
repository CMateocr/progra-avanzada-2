package com.prog_avanzada.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrder {

    private Long id;
    private LocalDateTime deliveredOn;
    private LocalDateTime placedOn;
    private Integer status;
    private Integer total;
    private Long customerId;

}
