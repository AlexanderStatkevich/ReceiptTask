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
        productList.add(Product.builder().setId(1L).setName("Milk").setPrice(new BigDecimal(2)).setSaleAmount(0.1).setIsOnSale(true).build());
        productList.add(Product.builder().setId(2L).setName("Bread").setPrice(new BigDecimal(3)).build());
        productList.add(Product.builder().setId(3L).setName("Meat").setPrice(new BigDecimal(15)).build());
        productList.add(Product.builder().setId(4L).setName("Cheese").setPrice(new BigDecimal(5)).build());
        productList.add(Product.builder().setId(5L).setName("Potato").setPrice(new BigDecimal(4)).setSaleAmount(0.1).setIsOnSale(true).build());
    }


    @Override
    public Product get(Long id) {
        return productList.stream()
                .filter(product -> product.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new ProductNotExistException("Incorrect id input"));
    }

    @Override
    public boolean exist(Long id) {
        for (Product product : productList) {
            if (product.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
