package com.statkevich.receipttask.reader;


import com.statkevich.receipttask.dto.InputPositionDto;
import com.statkevich.receipttask.dto.InputValueDto;

import java.util.ArrayList;
import java.util.List;

import static com.statkevich.receipttask.parser.ConsoleInputParser.CARD;

public class ConsoleReader implements Reader{
    @Override
    public InputValueDto read(List<String> orderList) {
        List<InputPositionDto> inputPositionDtoList = new ArrayList<>();
        String cardNumber = "0000";
        for (String orderRow : orderList) {
            String[] orderRowArray = orderRow.split("-");
            if (orderRow.contains(CARD)) {
                cardNumber = orderRowArray[1];
            } else {
                String id = orderRowArray[0];
                String quantity = orderRowArray[1];
                InputPositionDto inputPositionDto = new InputPositionDto(Long.valueOf(id), Integer.parseInt(quantity));
                inputPositionDtoList.add(inputPositionDto);
            }
        }
        return new InputValueDto(inputPositionDtoList, cardNumber);
    }
}