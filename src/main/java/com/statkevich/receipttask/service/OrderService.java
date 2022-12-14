package com.statkevich.receipttask.service;

import com.statkevich.receipttask.domain.DiscountCard;
import com.statkevich.receipttask.domain.Product;
import com.statkevich.receipttask.dto.OrderDto;
import com.statkevich.receipttask.dto.PositionDto;
import com.statkevich.receipttask.dto.ReceiptDto;
import com.statkevich.receipttask.dto.ReceiptRow;
import com.statkevich.receipttask.service.factories.DiscountCardServiceSingleton;
import com.statkevich.receipttask.service.factories.ProductServiceSingleton;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderService {


    ProductService productService = ProductServiceSingleton.getInstance();

    DiscountCardService discountCardService = DiscountCardServiceSingleton.getInstance();

    public ReceiptDto processingOrder(OrderDto orderDTO) {
        List<PositionDto> positionDtoList = orderDTO.getPositionDtoList();
        String cardNumber = orderDTO.getCardNumber();
        return receiptMakeOf(positionDtoList, cardNumber);
    }

    private ReceiptDto receiptMakeOf(List<PositionDto> positionDtoList,String cardNumber) {
        List<ReceiptRow> receiptRowList = new ArrayList<>();
        List<BigDecimal> totalSumList = new ArrayList<>();

        for (PositionDto positionDto : positionDtoList) {
            int id = positionDto.getId();
            int quantity = positionDto.getQuantity();

            Product product = productService.getProduct(id);
            BigDecimal price = product.getPrice();
            BigDecimal decimalSaleAmount = BigDecimal.valueOf(product.getSaleAmount());

            if (product.isOnSale() && quantity >= 5) {
                BigDecimal decimalQuantity = BigDecimal.valueOf(quantity);
                totalSumList.add(price.multiply(decimalQuantity).multiply(decimalSaleAmount));
            } else {
                totalSumList.add(price.multiply(BigDecimal.valueOf(quantity)));
            }

            ReceiptRow receiptRow = new ReceiptRow(quantity, product.getName(), price);
            receiptRowList.add(receiptRow);
        }

        BigDecimal totalSum = totalSumList.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal discountedTotalSum = discountApply(cardNumber, totalSum);

        return new ReceiptDto(receiptRowList,discountedTotalSum);
    }

    private BigDecimal discountApply(String cardNumber, BigDecimal totalSum) {
        DiscountCard discountCard = discountCardService.get(cardNumber);
        double discount = discountCard.getDiscount();
        return totalSum.multiply(BigDecimal.valueOf(1 - discount));
    }

}
