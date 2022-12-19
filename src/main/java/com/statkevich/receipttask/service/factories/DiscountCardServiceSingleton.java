package com.statkevich.receipttask.service.factories;

import com.statkevich.receipttask.dao.singletonfactories.DiscountCardDaoSingleton;
import com.statkevich.receipttask.service.DiscountCardService;

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