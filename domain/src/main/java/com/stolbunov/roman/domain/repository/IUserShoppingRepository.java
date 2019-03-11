package com.stolbunov.roman.domain.repository;

import com.stolbunov.roman.domain.entities.Product;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface IUserShoppingRepository {

    Completable save(Product product);

    Completable saveProductList(List<Product> products);

    Completable remove(String productId);

    Completable removeAll();

    Single<Product> getProduct(String productId);

    Single<List<Product>> getProducts();
}
