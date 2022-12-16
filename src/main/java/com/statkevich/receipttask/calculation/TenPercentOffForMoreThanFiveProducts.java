package com.statkevich.receipttask.calculation;

import com.statkevich.receipttask.domain.CommonProduct;
import com.statkevich.receipttask.domain.SaleType;
import com.statkevich.receipttask.dto.PositionDto;
import com.statkevich.receipttask.dto.ReceiptRow;


import java.math.BigDecimal;
import java.util.Set;

public class TenPercentOffForMoreThanFiveProducts extends CalculatorDecorator{


    private static final int MIN_QUANTITY_FOR_SALE = 5;
    private static final BigDecimal MULTIPLIER = (BigDecimal.valueOf(0.9));
    public TenPercentOffForMoreThanFiveProducts(Calculator calculator) {
        super(calculator);
    }

    @Override
    public ReceiptRow calculate(PositionDto position) {
        CommonProduct product = position.product();
        String name = product.getName();
        Set<SaleType> saleTypes = product.getSaleTypes();
        BigDecimal price = product.getPrice();
        int quantity = position.quantity();
        BigDecimal totalRowWithoutSale = price.multiply(BigDecimal.valueOf(quantity));
        BigDecimal totalRowSalePrice = price.multiply(BigDecimal.valueOf(quantity)).multiply(MULTIPLIER);
        BigDecimal salePercentage = BigDecimal.ONE.subtract(MULTIPLIER);
        BigDecimal saleAmount = totalRowWithoutSale.subtract(totalRowSalePrice);

        if (quantity > MIN_QUANTITY_FOR_SALE && saleTypes.contains(SaleType.TEN_PERCENT_OFF_FOR_MORE_THAN_FIVE_PRODUCTS)) {
            return new ReceiptRow(quantity,name,price,salePercentage,totalRowSalePrice,saleAmount);
        } else {
            return super.calculate(position);
        }
    }
}
