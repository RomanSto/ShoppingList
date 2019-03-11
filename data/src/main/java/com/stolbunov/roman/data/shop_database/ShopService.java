package com.stolbunov.roman.data.shop_database;

import com.stolbunov.roman.data.entities.ProductShop;
import com.stolbunov.roman.data.entities.counterfeit_factories.ProductShopFactory;

import java.util.List;

import javax.inject.Inject;

public class ShopService {
    private static List<ProductShop> products;

    @Inject
    public ShopService() {
        products = ProductShopFactory.getProductsShop();
    }

    public List<ProductShop> getProductList() {
        return products;
    }
}
