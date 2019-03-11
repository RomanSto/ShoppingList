package com.stolbunov.roman.data.mapper;

import com.google.firebase.database.DataSnapshot;
import com.stolbunov.roman.data.entities.ProductEntity;
import com.stolbunov.roman.domain.entities.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserShoppingMapper {
    public static final String KEY_PRODUCT_ID = "id";
    private static final String KEY_PRODUCT_NAME = "name";
    private static final String KEY_PRODUCT_URL_IMAGE = "urlImage";
    private static final String KEY_PRODUCT_PRICE = "price";

    public static Product transformToProduct(ProductEntity entity) {
        return new Product(
                entity.getId(),
                entity.getName(),
                entity.getUrlImage(),
                entity.getPrice());
    }

    public static Map<String, Object> transformProductToMap(Product product) {
        Map<String, Object> productMap = new HashMap<>();
        productMap.put(KEY_PRODUCT_ID, product.getId());
        productMap.put(KEY_PRODUCT_NAME, product.getName());
        productMap.put(KEY_PRODUCT_URL_IMAGE, product.getUrlImage());
        productMap.put(KEY_PRODUCT_PRICE, product.getPrice());
        return productMap;
    }

    public static List<Product> transformDataSnapshotToProducts(DataSnapshot dataSnapshot) {
        List<Product> list = new ArrayList<>();
        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
            list.add(transformDataSnapshotToProduct(snapshot));
        }
        return list;
    }

    public static List<Map<String, Object>> transformProductListToMap(List<Product> products) {
        List<Map<String, Object>> result = new ArrayList<>();

        for (Product product : products) {
            result.add(transformProductToMap(product));
        }

        return result;
    }

    public static Product transformDataSnapshotToProduct(DataSnapshot dataSnapshot) {
        ProductEntity entity = dataSnapshot.getValue(ProductEntity.class);
        return transformToProduct(entity);
    }
}
