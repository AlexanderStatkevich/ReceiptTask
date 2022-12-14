package com.statkevich.receipttask.domain;

public class DiscountCard {
    private String cardNumber;
    private double discount;

    public DiscountCard(String cardNumber, double discount) {
        this.cardNumber = cardNumber;
        this.discount = discount;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public double getDiscount() {
        return discount;
    }
}
