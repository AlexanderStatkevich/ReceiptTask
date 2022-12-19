package com.statkevich.receipttask.parser;

import com.statkevich.receipttask.domain.CommonProduct;
import com.statkevich.receipttask.dto.InputPositionDto;
import com.statkevich.receipttask.dto.InputValueDto;
import com.statkevich.receipttask.dto.OrderDto;
import com.statkevich.receipttask.dto.PositionDto;
import com.statkevich.receipttask.exceptions.OrderIsEmptyException;
import com.statkevich.receipttask.reader.ConsoleReader;
import com.statkevich.receipttask.reader.FileReader;
import com.statkevich.receipttask.service.ProductService;
import com.statkevich.receipttask.service.factories.ProductServiceSingleton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConsoleInputParser {
    public static final String CARD = "card";
    private final ProductService productService = ProductServiceSingleton.getInstance();
    private final ConsoleReader consoleReader = new ConsoleReader();
    private final com.statkevich.receipttask.reader.FileReader fileReader = new FileReader(consoleReader);


    public OrderDto parse(String[] order) {
        InputValueDto inputValuesDto = parseInternal(order);
        String cardNumber = inputValuesDto.cardNumber();
        List<PositionDto> positionDtoList = inputValuesDto.inputPositionDtoList()
                .stream()
                .map(this::getPositionDto)
                .collect(Collectors.toList());

        return new OrderDto(positionDtoList, cardNumber);
    }



    private PositionDto getPositionDto(InputPositionDto position) {
        CommonProduct product = productService.getProduct(position.id());
        return new PositionDto(product, position.quantity());
    }
    protected InputValueDto parseInternal(String[] order) {
        List<String> orderList = new ArrayList<>(Arrays.asList(order));
        if(orderList.isEmpty()){
            throw new OrderIsEmptyException("Nothing passed in order");
        }
        boolean parameterFile = orderList.stream()
                .anyMatch(row -> row.contains("file"));

        if (parameterFile) {
            return fileReader.read(orderList);
        } else {
            return consoleReader.read(orderList);
        }
    }
}
