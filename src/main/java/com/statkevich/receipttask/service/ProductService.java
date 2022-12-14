package com.statkevich.receipttask.service;

import com.statkevich.receipttask.dao.api.ProductDao;
import com.statkevich.receipttask.domain.Product;

public class ProductService {

    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }


    public Product getProduct(int id){
        return productDao.get(id);
    }

}
