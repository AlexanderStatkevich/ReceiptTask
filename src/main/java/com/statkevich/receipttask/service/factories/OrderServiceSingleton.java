package com.statkevich.receipttask.service.factories;

import com.statkevich.receipttask.service.OrderService;

public class OrderServiceSingleton {
    private volatile static OrderService instance;

    private OrderServiceSingleton() {
    }

    public static OrderService getInstance() {
        if (instance == null) {
            synchronized (OrderServiceSingleton.class) {
                if (instance == null) {
                    instance = new OrderService(DiscountCardServiceSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}
