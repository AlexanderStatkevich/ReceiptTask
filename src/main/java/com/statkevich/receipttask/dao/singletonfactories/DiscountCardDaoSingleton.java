package com.statkevich.receipttask.dao.singletonfactories;

import com.statkevich.receipttask.dao.SqlDiscountCardDao;
import com.statkevich.receipttask.dao.api.DiscountCardDao;

public class DiscountCardDaoSingleton {
    private volatile static DiscountCardDao INSTANCE;

    private DiscountCardDaoSingleton() {
    }

    public static DiscountCardDao getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (DiscountCardDaoSingleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SqlDiscountCardDao();
                }
            }
        }
        return INSTANCE;
    }
}