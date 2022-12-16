package com.statkevich.receipttask.dao.api;

import com.statkevich.receipttask.domain.DiscountCard;

public interface DiscountCardDao {
    DiscountCard get(String cardNumber);
}
