package com.statkevich.receipttask.dto;

public class PositionDto {

    private final int id;
    private final int quantity;

    public PositionDto(int id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }
}
