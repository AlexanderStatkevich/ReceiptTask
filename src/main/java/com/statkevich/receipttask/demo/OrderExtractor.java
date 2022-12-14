package com.statkevich.receipttask.demo;

import com.statkevich.receipttask.dto.OrderDto;
import com.statkevich.receipttask.dto.PositionDto;
import com.statkevich.receipttask.service.OrderService;
import com.statkevich.receipttask.service.factories.OrderServiceSingleton;

import java.util.ArrayList;
import java.util.List;

public class OrderExtractor {
    public static final String CARD = "card";

    public static OrderDto extractOrder(String [] order){
        List<PositionDto> positionDtoList = new ArrayList<>();
        String cardNumber = null;

        for(String orderRow: order){
            String[] split = orderRow.split("-");
            if(orderRow.contains(CARD)){
                cardNumber = split[1];
            }else {
                String id = split[0];
                String quantity = split[1];
                PositionDto positionDto = new PositionDto(Long.valueOf(id), Integer.parseInt(quantity));
                positionDtoList.add(positionDto);
            }
        }

       return new OrderDto(positionDtoList,cardNumber);

    }
}
