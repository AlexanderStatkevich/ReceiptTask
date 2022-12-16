package com.statkevich.receipttask.view;

import com.statkevich.receipttask.dto.ReceiptDto;
import com.statkevich.receipttask.dto.ReceiptRow;

import java.math.BigDecimal;
import java.util.List;

public class PrepareStringToPrintUtil {

    public static String prepareReceipt(ReceiptDto receiptDto) {

        StringBuilder receiptForPrint = new StringBuilder();
        receiptForPrint
                .append("Quant ")
                .append("Name ")
                .append("Price ")
                .append("TR ")
                .append("Sale ")
                .append("SA")
                .append("\n");;
        List<ReceiptRow> receiptRowList = receiptDto.receiptRow();
        for (ReceiptRow receiptRow : receiptRowList) {
            int quantity = receiptRow.quantity();
            String productName = receiptRow.productName();
            BigDecimal price = receiptRow.price();
            BigDecimal totalRowPrice = receiptRow.totalRow();
            BigDecimal salePercentage = (receiptRow.salePercentage() == null)
                    ? BigDecimal.valueOf(0)
                    : receiptRow.salePercentage().multiply(BigDecimal.valueOf(100));
            BigDecimal saleAmount = (receiptRow.saleAmount() == null)
                    ? BigDecimal.valueOf(0)
                    : receiptRow.saleAmount();

            receiptForPrint
                    .append(quantity)
                    .append(" ")
                    .append(productName)
                    .append(" ")
                    .append(price)
                    .append(" ")
                    .append(totalRowPrice)
                    .append(" -")
                    .append(salePercentage)
                    .append("% ")
                    .append(saleAmount)
                    .append("\n");
        }

        BigDecimal total = receiptDto.total();
        receiptForPrint
                .append("Total: ")
                .append(total);
        return receiptForPrint.toString();
    }
}
