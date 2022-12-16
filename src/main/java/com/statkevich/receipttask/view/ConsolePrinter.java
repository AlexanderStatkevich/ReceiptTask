package com.statkevich.receipttask.view;

import com.statkevich.receipttask.dto.ReceiptDto;
import com.statkevich.receipttask.dto.ReceiptRow;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

public class ConsolePrinter implements Printer {
    @Override
    public void print(String receipt) {
        PrintWriter writer = new PrintWriter(System.out);
        writer.print(receipt);
        writer.flush();
    }


}
