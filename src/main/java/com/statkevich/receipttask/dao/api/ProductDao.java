package com.statkevich.receipttask.dao.api;

import com.statkevich.receipttask.domain.CommonProduct;

public interface ProductDao {
    CommonProduct get(Long id);

}
