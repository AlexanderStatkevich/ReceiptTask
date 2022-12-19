package com.statkevich.receipttask.dao.singletonfactories;

import com.statkevich.receipttask.dao.SqlProductDao;
import com.statkevich.receipttask.dao.api.ProductDao;

public class ProductDaoSingleton {

        private volatile static ProductDao INSTANCE;

        private ProductDaoSingleton() {
        }

        public static ProductDao getINSTANCE() {
            if (INSTANCE == null) {
                synchronized (ProductDao.class) {
                    if (INSTANCE == null) {
                        INSTANCE = new SqlProductDao();
                    }
                }
            }
            return INSTANCE;
        }
    }