package com.statkevich.receipttask.domain;

import java.math.BigDecimal;

public class Product {
    private int id;
    private String name;
    private BigDecimal price;
    private double saleAmount;
    private boolean isOnSale;

    public Product(int id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product(int id, String name, BigDecimal price, boolean isOnSale) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isOnSale = isOnSale;
    }

    public static ProductBuilder builder() {
        return new ProductBuilder();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isOnSale() {
        return isOnSale;
    }

    public double getSaleAmount() {
        return saleAmount;
    }

    public static class ProductBuilder {
        int id;
        String name;
        BigDecimal price;
        boolean isOnSale;

        private ProductBuilder() {
        }

        public ProductBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public ProductBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder setAddress(BigDecimal price) {
            this.price = price;
            return this;
        }

        public ProductBuilder setIsOnSale(boolean isOnSale) {
            this.isOnSale = isOnSale;
            return this;
        }

        public Product build() {
            return new Product(id, name, price,isOnSale);
        }

    }
}
