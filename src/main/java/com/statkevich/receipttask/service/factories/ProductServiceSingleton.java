package com.statkevich.receipttask.service.factories;

import com.statkevich.receipttask.dao.factories.ProductDaoSingleton;
import com.statkevich.receipttask.service.OrderService;
import com.statkevich.receipttask.service.ProductService;

public class ProductServiceSingleton {
    private volatile static ProductService instance;

    private ProductServiceSingleton() {
    }

    public static ProductService getInstance() {
        if (instance == null) {
            synchronized (ProductServiceSingleton.class) {
                if (instance == null) {
                    instance = new ProductService(ProductDaoSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}