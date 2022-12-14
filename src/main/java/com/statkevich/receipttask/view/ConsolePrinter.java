package com.statkevich.receipttask.view;

import com.statkevich.receipttask.dto.ReceiptDto;
import com.statkevich.receipttask.dto.ReceiptRow;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

public class ConsolePrinter implements Printer {
    @Override
    public void print(ReceiptDto receiptDto) {

        StringBuilder receiptForPrint = new StringBuilder();
        List<ReceiptRow> receiptRowList = receiptDto.receiptRow();


        for (ReceiptRow receiptRow : receiptRowList) {
            int quantity = receiptRow.quantity();
            String productName = receiptRow.productName();
            BigDecimal price = receiptRow.price();
            BigDecimal totalRowPrice = price.multiply(BigDecimal.valueOf(quantity));
            receiptForPrint
                    .append(quantity)
                    .append(" ")
                    .append(productName)
                    .append(" ")
                    .append(price)
                    .append(" ")
                    .append(totalRowPrice)
                    .append("\n");
        }

        BigDecimal total = receiptDto.total();
        receiptForPrint
                .append("Total: ")
                .append(total);
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out));
        writer.write(String.valueOf(receiptForPrint));

//        new BufferedWriter(System.out);
    }


}
