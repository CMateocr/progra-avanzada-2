package com.programacion.avanzada.domain.dto;

import java.math.BigDecimal;

public record BookTitlePriceSoldDto(String title, BigDecimal price, Integer sold) {
}
