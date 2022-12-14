package com.statkevich.receipttask.domain;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscountCard that = (DiscountCard) o;
        return Double.compare(that.discount, discount) == 0 && Objects.equals(cardNumber, that.cardNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, discount);
    }

    @Override
    public String toString() {
        return "DiscountCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", discount=" + discount +
                '}';
    }
}
