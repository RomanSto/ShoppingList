package com.stolbunov.roman.domain.repository;

import com.stolbunov.roman.domain.entities.Product;

import java.util.List;

import io.reactivex.Single;

public interface IShopServiceRepository {

    Single<List<Product>> getShopProductList();
}
