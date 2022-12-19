package com.statkevich.receipttask.dao.singletonfactories;

import com.statkevich.receipttask.dao.SqlProductDao;
import com.statkevich.receipttask.dao.api.ProductDao;

public class ProductDaoSingleton {

        private volatile static ProductDao instance;

        private ProductDaoSingleton() {
        }

        public static ProductDao getInstance() {
            if (instance == null) {
                synchronized (ProductDao.class) {
                    if (instance == null) {
                        instance = new SqlProductDao();
                    }
                }
            }
            return instance;
        }
    }