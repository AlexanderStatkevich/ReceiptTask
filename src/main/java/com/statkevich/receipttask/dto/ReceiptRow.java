package com.statkevich.receipttask.dto;

import java.math.BigDecimal;

public class ReceiptRow {
    private final int quantity;
    private final String productName;
    private final BigDecimal price;

    public ReceiptRow(int quantity, String productName, BigDecimal price) {
        this.quantity = quantity;
        this.productName = productName;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
