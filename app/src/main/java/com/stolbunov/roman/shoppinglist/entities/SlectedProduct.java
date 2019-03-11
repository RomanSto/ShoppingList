package com.stolbunov.roman.shoppinglist.entities;

import com.stolbunov.roman.domain.entities.Product;

public class SlectedProduct {
    private boolean isChoose;
    private final Product product;

    public SlectedProduct(boolean isChoose, Product product) {
        this.isChoose = isChoose;
        this.product = product;
    }
}
