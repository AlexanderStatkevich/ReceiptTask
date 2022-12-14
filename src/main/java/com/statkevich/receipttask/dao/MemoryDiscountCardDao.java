package com.statkevich.receipttask.dao;

import com.statkevich.receipttask.dao.api.DiscountCardDao;
import com.statkevich.receipttask.domain.DiscountCard;

import java.util.ArrayList;
import java.util.List;

public class MemoryDiscountCardDao implements DiscountCardDao {

    private final List<DiscountCard> cardList = new ArrayList<>();

    {
        cardList.add(new DiscountCard("1124", 0.03));
        cardList.add(new DiscountCard("1224", 0));
        cardList.add(new DiscountCard("1134", 0.05));
        cardList.add(new DiscountCard("4524", 0.07));
        cardList.add(new DiscountCard("0614", 0.1));
    }


    public DiscountCard get(String cardName) {
        for (DiscountCard discountCard : cardList) {
            return discountCard;
        }
        return null;
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
