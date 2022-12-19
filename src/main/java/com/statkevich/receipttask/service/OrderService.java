package com.statkevich.receipttask.service;

import com.statkevich.receipttask.calculation.DiscountCardDecorator;
import com.statkevich.receipttask.calculation.FullCostCalculator;
import com.statkevich.receipttask.calculation.TenPercentOffForMoreThanFiveProducts;
import com.statkevich.receipttask.domain.DiscountCard;
import com.statkevich.receipttask.dto.OrderDto;
import com.statkevich.receipttask.dto.PositionDto;
import com.statkevich.receipttask.dto.ReceiptDto;
import com.statkevich.receipttask.dto.ReceiptRow;
import com.statkevich.receipttask.service.factories.DiscountCardServiceSingleton;
import com.statkevich.receipttask.service.factories.ProductServiceSingleton;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class OrderService {
    private final DiscountCardService discountCardService;

    public OrderService(DiscountCardService discountCardService) {
        this.discountCardService = discountCardService;
    }

    public ReceiptDto processingOrder(OrderDto orderDTO) {
        List<PositionDto> positionDtoList = orderDTO.positionDtoList();
        String cardNumber = orderDTO.cardNumber();
        List<ReceiptRow> receiptRowList = receiptMakeOf(positionDtoList, cardNumber);
        BigDecimal total = countTotal(receiptRowList);
        return new ReceiptDto(receiptRowList, total);
    }

    private List<ReceiptRow> receiptMakeOf(List<PositionDto> positionDtoList, String cardNumber) {
        DiscountCard discountCard = discountCardService.get(cardNumber);
        return positionDtoList.stream()
                .map(position -> getReceiptRow(position, discountCard))
                .collect(Collectors.toList());
    }

    private ReceiptRow getReceiptRow(PositionDto positionDto, DiscountCard discountCard) {
        DiscountCardDecorator receiptRowSaleAndDiscountEvaluation =
                new DiscountCardDecorator(new TenPercentOffForMoreThanFiveProducts(new FullCostCalculator()), discountCard);
        return receiptRowSaleAndDiscountEvaluation.calculate(positionDto);
    }

    private BigDecimal countTotal(List<ReceiptRow> receiptRowList) {
        return receiptRowList.stream()
                .map(ReceiptRow::totalRow)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
