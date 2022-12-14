package com.statkevich.receipttask.dto;


import java.math.BigDecimal;
import java.util.List;

public class ReceiptDto {

    private final List<ReceiptRow> receiptRow;

    private final BigDecimal total;

    public ReceiptDto(List<ReceiptRow> receiptRow, BigDecimal total) {
        this.receiptRow = receiptRow;
        this.total = total;
    }

    public List<ReceiptRow> getReceiptRow() {
        return receiptRow;
    }

    public BigDecimal getTotal() {
        return total;
    }
}
