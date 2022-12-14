package com.statkevich.receipttask.demo;


import com.statkevich.receipttask.dto.OrderDto;
import com.statkevich.receipttask.dto.ReceiptDto;
import com.statkevich.receipttask.service.OrderService;
import com.statkevich.receipttask.service.factories.OrderServiceSingleton;
import com.statkevich.receipttask.view.ConsolePrinter;
import com.statkevich.receipttask.view.Printer;

public class Runner {
    public static void main(String[] args) {

        OrderService orderService = OrderServiceSingleton.getInstance();
        OrderDto orderDto = OrderExtractor.extractOrder(args);
        ReceiptDto receiptDto = orderService.processingOrder(orderDto);
        Printer printer = new ConsolePrinter();
        printer.print(receiptDto);

    }
}
