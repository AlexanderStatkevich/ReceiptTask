package com.statkevich.receipttask.dto;

import java.math.BigDecimal;

public record ReceiptRow(int quantity, String productName, BigDecimal price, BigDecimal salePercentage,BigDecimal totalRow, BigDecimal saleAmount) {
}
