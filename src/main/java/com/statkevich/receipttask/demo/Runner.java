package com.statkevich.receipttask.demo;


import com.statkevich.receipttask.dto.OrderDto;
import com.statkevich.receipttask.dto.ReceiptDto;
import com.statkevich.receipttask.exceptions.DiscountCardNotExistException;
import com.statkevich.receipttask.exceptions.OrderIsEmptyException;
import com.statkevich.receipttask.exceptions.ProductNotExistException;
import com.statkevich.receipttask.parser.ConsoleInputParser;
import com.statkevich.receipttask.service.OrderService;
import com.statkevich.receipttask.service.factories.OrderServiceSingleton;
import com.statkevich.receipttask.view.ConsolePrinterFactory;
import com.statkevich.receipttask.view.FilePrinterFactory;
import com.statkevich.receipttask.view.PrepareStringToPrintUtil;
import com.statkevich.receipttask.view.Printer;
import com.statkevich.receipttask.view.PrinterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Runner {

    private static final String PRINT_TYPE = "console";
    private static final Logger LOGGER = LoggerFactory.getLogger(Runner.class);

    public static void main(String[] args) {
        ConsoleInputParser consoleInputParser = new ConsoleInputParser();
        PrinterFactory printerFactory = factoryRespectivelyProperties();
        Printer printer = printerFactory.createPrinter();
        try {
            OrderDto orderDto = consoleInputParser.parse(args);
            OrderService orderService = OrderServiceSingleton.getInstance();
            ReceiptDto receiptDto = orderService.processingOrder(orderDto);
            String receipt = PrepareStringToPrintUtil.prepareReceipt(receiptDto);
            printer.print(receipt);

        } catch (ProductNotExistException | OrderIsEmptyException | DiscountCardNotExistException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private static PrinterFactory factoryRespectivelyProperties() {
        if (PRINT_TYPE.equals("file")) {
            return new FilePrinterFactory();
        } else
            return new ConsolePrinterFactory();
    }

}


