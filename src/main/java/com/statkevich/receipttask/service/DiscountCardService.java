package com.statkevich.receipttask.service;

import com.statkevich.receipttask.dao.api.DiscountCardDao;
import com.statkevich.receipttask.domain.DiscountCard;

public class DiscountCardService {

private final DiscountCardDao discountCardDao;
    public DiscountCardService(DiscountCardDao discountCardDao) {
        this.discountCardDao = discountCardDao;
    }

    public boolean exist(String cardName){
        return discountCardDao.exist(cardName);
    }

    public DiscountCard get(String cardName){
        return discountCardDao.get(cardName);
    }

}
