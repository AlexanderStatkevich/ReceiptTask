package com.statkevich.receipttask.service;

import com.statkevich.receipttask.dao.api.DiscountCardDao;
import com.statkevich.receipttask.dao.singletonfactories.DiscountCardDaoSingleton;
import com.statkevich.receipttask.domain.CommonProduct;
import com.statkevich.receipttask.domain.SaleType;
import com.statkevich.receipttask.dto.OrderDto;
import com.statkevich.receipttask.dto.PositionDto;
import com.statkevich.receipttask.dto.ReceiptDto;
import com.statkevich.receipttask.dto.ReceiptRow;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


class OrderServiceTest {

    private final DiscountCardDao discountCardDao = DiscountCardDaoSingleton.getInstance();
    private final DiscountCardService discountCardService = new DiscountCardService(discountCardDao);
    private final OrderService orderService = new OrderService(discountCardService);
    private final OrderDto orderDto;

    public OrderServiceTest() {
        orderDto = new OrderDto(List.of(
                new PositionDto(new CommonProduct(1L, "Name1", BigDecimal.valueOf(5), null), 5),
                new PositionDto(new CommonProduct(2L, "Name2", BigDecimal.valueOf(10), Set.of(SaleType.TEN_PERCENT_OFF_FOR_MORE_THAN_FIVE_PRODUCTS)), 7)), "0000");
    }


    @Test
    void testOrder() {
        ReceiptDto receiptDto = orderService.processingOrder(orderDto);
        assertEquals(new ReceiptDto(List.of(new ReceiptRow(5, "Name1", BigDecimal.valueOf(5), BigDecimal.ZERO, BigDecimal.valueOf(25), BigDecimal.ZERO),
                new ReceiptRow(7, "Name2", BigDecimal.valueOf(10), BigDecimal.TEN, BigDecimal.valueOf(63), BigDecimal.valueOf(7))), BigDecimal.valueOf(88)), receiptDto);
    }
}