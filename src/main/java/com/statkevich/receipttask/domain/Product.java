package com.statkevich.receipttask.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private final Long id;
    private final String name;
    private final BigDecimal price;
    private final double saleAmount;
    private final boolean isOnSale;


    public Product(Long id, String name, BigDecimal price, double saleAmount, boolean isOnSale) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.saleAmount = saleAmount;
        this.isOnSale = isOnSale;
    }

    public static ProductBuilder builder() {
        return new ProductBuilder();
    }

    public Long getId() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.saleAmount, saleAmount) == 0 && isOnSale == product.isOnSale && Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, saleAmount, isOnSale);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", saleAmount=" + saleAmount +
                ", isOnSale=" + isOnSale +
                '}';
    }

    public static class ProductBuilder {
        Long id;
        String name;
        BigDecimal price;
        private double saleAmount;
        boolean isOnSale;

        private ProductBuilder() {
        }

        public ProductBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public ProductBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder setPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public ProductBuilder setIsOnSale(boolean isOnSale) {
            this.isOnSale = isOnSale;
            return this;
        }
        public ProductBuilder setSaleAmount(double saleAmount) {
            this.saleAmount = saleAmount;
            return this;
        }

        public Product build() {
            return new Product(id, name, price,saleAmount,isOnSale);
        }

    }
}
