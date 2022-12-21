package com.statkevich.receipttask.domain;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class CommonProduct {
    private final Long id;
    private final String name;
    private final BigDecimal price;

    private final Set<SaleType> saleTypes;

    public CommonProduct(Long id, String name, BigDecimal price, Set<SaleType> saleTypes) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.saleTypes = saleTypes;
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

    public Set<SaleType> getSaleTypes() {
        return saleTypes;
    }

    public static class ProductBuilder {
        Long id;
        String name;
        BigDecimal price;
        Set<SaleType> saleTypes;

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
        public ProductBuilder setSaleType(Set<SaleType> saleTypes) {
            this.saleTypes = saleTypes;
            return this;
        }

        public CommonProduct build() {
            if(saleTypes == null){
                saleTypes = Collections.emptySet();
            }
            return new CommonProduct(id, name, price,saleTypes);
        }

    }
}
