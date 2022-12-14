package com.statkevich.receipttask.dao;

import com.statkevich.receipttask.dao.api.DiscountCardDao;
import com.statkevich.receipttask.domain.DiscountCard;
import com.statkevich.receipttask.exceptions.DiscountCardNotExistException;

import java.util.ArrayList;
import java.util.List;

public class MemoryDiscountCardDao implements DiscountCardDao {

    private final List<DiscountCard> cardList = new ArrayList<>();

    {
        cardList.add(new DiscountCard("1111", 0.03));
        cardList.add(new DiscountCard("2222", 0));
        cardList.add(new DiscountCard("3333", 0.05));
        cardList.add(new DiscountCard("4444", 0.07));
        cardList.add(new DiscountCard("5555", 0.1));
    }



    public DiscountCard get(String cardNumber) {
        return cardList.stream()
                .filter(discountCard -> discountCard.getCardNumber().equals(cardNumber))
                .findAny()
                .orElseThrow(()-> new DiscountCardNotExistException("Incorrect card number input"));
    }


    public boolean exist(String cardName) {
        for (DiscountCard discountCard : cardList) {
            if (discountCard.getCardNumber().equals(cardName)) {
                return true;
            }
        }
        return false;
    }
}
