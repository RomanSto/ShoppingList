package com.stolbunov.roman.domain.use_cases;

import com.stolbunov.roman.domain.entities.Product;

import java.util.List;

import io.reactivex.Single;

public interface IShopServiceUseCase {

    Single<List<Product>> getShopProductList();
}
