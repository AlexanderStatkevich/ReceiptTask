package com.statkevich.receipttask.dao.api;

import com.statkevich.receipttask.domain.Product;

public interface ProductDao {

    Product get(int id);

}
