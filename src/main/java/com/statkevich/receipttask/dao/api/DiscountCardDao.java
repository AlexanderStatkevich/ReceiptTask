package com.statkevich.receipttask.dao.api;

import com.statkevich.receipttask.domain.DiscountCard;

public interface DiscountCardDao {

    boolean exist(String card);
    DiscountCard get(String card);
}
