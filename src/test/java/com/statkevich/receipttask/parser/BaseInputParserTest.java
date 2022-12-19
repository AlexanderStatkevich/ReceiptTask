package com.statkevich.receipttask.parser;

import com.statkevich.receipttask.dao.SqlProductDao;
import com.statkevich.receipttask.dao.api.ProductDao;
import com.statkevich.receipttask.domain.CommonProduct;
import com.statkevich.receipttask.dto.OrderDto;
import com.statkevich.receipttask.dto.PositionDto;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BaseInputParserTest {

    private final BaseInputParser<String[]> consoleInputParser = new ConsoleInputParser();
    private final BaseInputParser<Map<String, String[]>> webInputParser = new WebInputParser();

    ProductDao productDao = new SqlProductDao();




    @Test
    void consoleInputParserTest() {
        String[] orderArray = new String[]{"1-2","card-1234"};
        OrderDto orderDto = consoleInputParser.parse(orderArray);
        CommonProduct product = productDao.get(1L);
        assertEquals(new OrderDto(List.of(
                new PositionDto(product,2)),
                "1234"),orderDto);
    }

    @Test
    void webInputParserTest() {
        Map<String,String[]> orderMap = new HashMap<>();
        orderMap.put("1",new String[] {"2"});
        orderMap.put("card",new String[] {"1234"});
        OrderDto orderDto = webInputParser.parse(orderMap);
        CommonProduct product = productDao.get(1L);
        assertEquals(new OrderDto(List.of(
                new PositionDto(product,2)),
                "1234"),orderDto);
    }


}