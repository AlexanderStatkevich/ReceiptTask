package com.statkevich.receipttask.dao;

import com.statkevich.receipttask.dao.api.DiscountCardDao;
import com.statkevich.receipttask.domain.DiscountCard;
import com.statkevich.receipttask.exceptions.DiscountCardNotExistException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
/**
 * @deprecated replaced with {@link SqlDiscountCardDao}
 */
//@Deprecated
public class MemoryDiscountCardDao implements DiscountCardDao {

    private final List<DiscountCard> cardList = new ArrayList<>();

    {
        cardList.add(new DiscountCard("0000", BigDecimal.valueOf(0.0)));
        cardList.add(new DiscountCard("1111", BigDecimal.valueOf(0.03)));
        cardList.add(new DiscountCard("2222", BigDecimal.valueOf(0)));
        cardList.add(new DiscountCard("3333", BigDecimal.valueOf(0.05)));
        cardList.add(new DiscountCard("4444", BigDecimal.valueOf(0.07)));
        cardList.add(new DiscountCard("5555", BigDecimal.valueOf(0.09)));
    }

    public DiscountCard get(String cardNumber) {
        return cardList.stream()
                .filter(discountCard -> discountCard.getCardNumber().equals(cardNumber))
                .findAny()
                .orElseThrow(()-> new DiscountCardNotExistException("Incorrect card number input"));
    }
}
