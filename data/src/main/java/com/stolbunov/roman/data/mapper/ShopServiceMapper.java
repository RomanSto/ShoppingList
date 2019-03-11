package com.stolbunov.roman.data.mapper;

import com.stolbunov.roman.data.entities.ProductShop;
import com.stolbunov.roman.domain.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class ShopServiceMapper {

    public static List<Product> transformToProductList(List<ProductShop> productShopList) {
        List<Product> result = new ArrayList<>(productShopList.size());

        for (ProductShop productShop : productShopList) {
            result.add(transformToProduct(productShop));
        }
        return result;
    }

    public static Product transformToProduct(ProductShop productShop) {
        return new Product(
                productShop.getId(),
                productShop.getName(),
                productShop.getUrlImage(),
                productShop.getPrice());
    }
}
