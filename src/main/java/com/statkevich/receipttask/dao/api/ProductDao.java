package com.statkevich.receipttask.dao.api;

import com.statkevich.receipttask.domain.Product;

public interface ProductDao {
    boolean exist(Long id);
    Product get(Long id);

}
