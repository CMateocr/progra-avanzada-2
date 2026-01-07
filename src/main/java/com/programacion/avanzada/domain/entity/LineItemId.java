package com.programacion.avanzada.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode
public class LineItemId implements Serializable {
  private Long idx;
  private Long purchaseOrder;
}
