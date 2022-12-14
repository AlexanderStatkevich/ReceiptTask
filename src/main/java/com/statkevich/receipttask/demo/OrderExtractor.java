package com.statkevich.receipttask.demo;

import com.statkevich.receipttask.dto.OrderDto;
import com.statkevich.receipttask.dto.PositionDto;
import com.statkevich.receipttask.service.OrderService;
import com.statkevich.receipttask.service.factories.OrderServiceSingleton;

import java.util.ArrayList;
import java.util.List;

public class OrderExtractor {

   private static final OrderService orderService = OrderServiceSingleton.getInstance();

    public static void extractOrder(String [] order){
        String cardNumber = "14445";
        PositionDto positionDto1 = new PositionDto(1,5);
        PositionDto positionDto2 = new PositionDto(2,6);
        PositionDto positionDto3 = new PositionDto(3,7);


        List<PositionDto> positionDtoList = new ArrayList<>();
        positionDtoList.add(positionDto1);
        positionDtoList.add(positionDto2);
        positionDtoList.add(positionDto3);

        OrderDto orderDto = new OrderDto(positionDtoList,cardNumber);
        orderService.processingOrder(orderDto);
    }
}
