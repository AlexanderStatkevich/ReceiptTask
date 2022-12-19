package com.statkevich.receipttask.dao.singletonfactories;

import com.statkevich.receipttask.dao.SqlDiscountCardDao;
import com.statkevich.receipttask.dao.api.DiscountCardDao;

public class DiscountCardDaoSingleton {
    private volatile static DiscountCardDao instance;

    private DiscountCardDaoSingleton() {
    }

    public static DiscountCardDao getInstance() {
        if (instance == null) {
            synchronized (DiscountCardDaoSingleton.class) {
                if (instance == null) {
                    instance = new SqlDiscountCardDao();
                }
            }
        }
        return instance;
    }
}