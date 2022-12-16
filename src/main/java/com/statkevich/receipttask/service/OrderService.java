package com.statkevich.receipttask.service;

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
    DiscountCardService discountCardService = DiscountCardServiceSingleton.getInstance();

    public ReceiptDto processingOrder(OrderDto orderDTO) {
        List<PositionDto> positionDtoList = orderDTO.positionDtoList();
        String cardNumber = orderDTO.cardNumber();
        List<ReceiptRow> receiptRowList = receiptMakeOf(positionDtoList);
        BigDecimal total = countTotal(receiptRowList, cardNumber);
        return new ReceiptDto(receiptRowList, total);
    }

    private List<ReceiptRow> receiptMakeOf(List<PositionDto> positionDtoList) {
        return positionDtoList.stream()
                .map(this::getReceiptRow)
                .collect(Collectors.toList());
    }

    private ReceiptRow getReceiptRow(PositionDto positionDto) {
        TenPercentOffForMoreThanFiveProducts tenPercentOffForMoreThanFiveProducts = new TenPercentOffForMoreThanFiveProducts(new FullCostCalculator());
        return tenPercentOffForMoreThanFiveProducts.calculate(positionDto);
    }

    private BigDecimal countTotal(List<ReceiptRow> receiptRowList, String cardNumber) {
        List<BigDecimal> totalSumList = new ArrayList<>();
        DiscountCard discountCard = discountCardService.get(cardNumber);
        BigDecimal discountMultiplier = BigDecimal.ONE.subtract(discountCard.getDiscount());

        for (ReceiptRow receiptRow : receiptRowList) {
            if (receiptRow.salePercentage() == null) {
                totalSumList.add(receiptRow.totalRow().multiply(discountMultiplier));
            } else {
                totalSumList.add(receiptRow.totalRow());
            }
        }
        return totalSumList.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
