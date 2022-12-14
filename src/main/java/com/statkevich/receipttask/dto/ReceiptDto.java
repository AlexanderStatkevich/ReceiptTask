package com.statkevich.receipttask.dto;


import java.math.BigDecimal;
import java.util.List;

public record ReceiptDto(List<ReceiptRow> receiptRow, BigDecimal total) {

}
