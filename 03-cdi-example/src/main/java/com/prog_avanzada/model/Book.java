package com.prog_avanzada.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal; // <-- Importación necesaria

@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Book {
  private String isbn;
  private String title;
  private BigDecimal price;
  private Integer version;

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public String getIsbn() {
    return isbn;
  }

  public String getTitle() {
    return title;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public Integer getVersion() {
    return version;
  }
}