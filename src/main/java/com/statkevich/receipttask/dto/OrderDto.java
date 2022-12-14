package com.statkevich.receipttask.dto;

import java.util.List;

public class OrderDto {
    private final List<PositionDto> positionDtoList;
    private final String cardNumber;

    public OrderDto(List<PositionDto> positionDtoList, String cardNumber) {
        this.positionDtoList = positionDtoList;
        this.cardNumber = cardNumber;
    }

    public List<PositionDto> getPositionDtoList() {
        return positionDtoList;
    }

    public String getCardNumber() {
        return cardNumber;
    }
}
