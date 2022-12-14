package com.statkevich.receipttask.dao;

import com.statkevich.receipttask.dao.api.ProductDao;
import com.statkevich.receipttask.domain.Product;
import com.statkevich.receipttask.exceptions.ProductNotExistException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MemoryProductDao implements ProductDao {

    private final List<Product> productList = new ArrayList<>();

    {
        productList.add(new Product(1,"Milk", new BigDecimal(2)));
        productList.add(new Product(2,"Bread", new BigDecimal(3)));
        productList.add(new Product(3,"Meat", new BigDecimal(15)));
        productList.add(new Product(4,"Cheese", new BigDecimal(5)));
        productList.add(new Product(5,"Potato", new BigDecimal(4)));
    }


    @Override
    public Product get(int id) {
        return productList.stream()
                .filter(x->x.getId() == id)
                .findAny()
                .orElseThrow(()-> new ProductNotExistException("Incorrect id input"));
    }
}
