package com.statkevich.receipttask.demo;


import com.statkevich.receipttask.dto.OrderDto;
import com.statkevich.receipttask.dto.ReceiptDto;
import com.statkevich.receipttask.service.OrderService;
import com.statkevich.receipttask.service.factories.OrderServiceSingleton;
import com.statkevich.receipttask.view.ConsolePrinterFactory;
import com.statkevich.receipttask.view.FilePrinterFactory;
import com.statkevich.receipttask.view.PrepareStringToPrintUtil;
import com.statkevich.receipttask.view.Printer;
import com.statkevich.receipttask.view.PrinterFactory;


public class Runner {
    public static void main(String[] args) {

        OrderService orderService = OrderServiceSingleton.getInstance();
        OrderExtractor orderExtractor = new OrderExtractor();
        OrderDto orderDto = orderExtractor.extractOrder(args);
        ReceiptDto receiptDto = orderService.processingOrder(orderDto);

        String receipt = PrepareStringToPrintUtil.prepareReceipt(receiptDto);

        PrinterFactory printerFactory = new FilePrinterFactory();
        Printer printer = printerFactory.createPrinter();
        printer.print(receipt);
    }
}

