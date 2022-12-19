package com.statkevich.receipttask.parser;

import com.statkevich.receipttask.domain.CommonProduct;
import com.statkevich.receipttask.dto.InputPositionDto;
import com.statkevich.receipttask.dto.InputValuesDto;
import com.statkevich.receipttask.dto.OrderDto;
import com.statkevich.receipttask.dto.PositionDto;
import com.statkevich.receipttask.service.ProductService;
import com.statkevich.receipttask.service.singletonfactories.ProductServiceSingleton;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseInputParser<T> implements InputParser<T> {
    public static final String CARD = "card";
    private final ProductService productService = ProductServiceSingleton.getINSTANCE();

    @Override
    public OrderDto parse(T input) {
        InputValuesDto inputValuesDto = parseInternal(input);
        String cardNumber = inputValuesDto.cardNumber();
        List<PositionDto> positionDtoList = inputValuesDto.inputPositionDtoList()
                .stream()
                .map(this::getPositionDto)
                .collect(Collectors.toList());

        return new OrderDto(positionDtoList, cardNumber);
    }

    protected abstract InputValuesDto parseInternal(T input);

    private PositionDto getPositionDto(InputPositionDto position) {
        CommonProduct product = productService.getProduct(position.id());
        return new PositionDto(product, position.quantity());
    }
}
