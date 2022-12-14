package com.statkevich.receipttask.dao.api;

import com.statkevich.receipttask.domain.DiscountCard;

public interface DiscountCardDao {
    boolean exist(String cardNumber);
    DiscountCard get(String cardNumber);
}
