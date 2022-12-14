package com.statkevich.receipttask.service.factories;

import com.statkevich.receipttask.dao.factories.DiscountCardDaoSingleton;
import com.statkevich.receipttask.domain.DiscountCard;
import com.statkevich.receipttask.service.DiscountCardService;
import com.statkevich.receipttask.service.OrderService;

public class DiscountCardServiceSingleton {
    private volatile static DiscountCardService instance;

    private DiscountCardServiceSingleton() {
    }

    public static DiscountCardService getInstance() {
        if (instance == null) {
            synchronized (DiscountCardServiceSingleton.class) {
                if (instance == null) {
                    instance = new DiscountCardService(DiscountCardDaoSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}