package com.stolbunov.roman.domain.use_cases;

import com.stolbunov.roman.domain.entities.Credentials;
import com.stolbunov.roman.domain.entities.Product;
import com.stolbunov.roman.domain.repository.IAuthorizationRepository;
import com.stolbunov.roman.domain.repository.IShopServiceRepository;
import com.stolbunov.roman.domain.repository.IUserShoppingRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

public class ShoppingInteraptor implements IAuthUseCase, IUserShoppingUseCase, IShopServiceUseCase {
    private final IAuthorizationRepository auth;
    private final IUserShoppingRepository repository;
    private final IShopServiceRepository shop;


    @Inject
    public ShoppingInteraptor(IAuthorizationRepository auth, IUserShoppingRepository repository,
                              IShopServiceRepository shop) {
        this.repository = repository;
        this.auth = auth;
        this.shop = shop;
    }

    @Override
    public Completable createUserWithEmailAndPassword(Credentials credentials) {
        return auth.createUserWithEmailAndPassword(credentials);
    }

    @Override
    public Completable signInWithEmailAndPassword(Credentials credentials) {
        return auth.signInWithEmailAndPassword(credentials);
    }

    @Override
    public boolean isCurrentUser() {
        return auth.isCurrentUser();
    }

    @Override
    public void signOut() {
        auth.signOut();
    }

    @Override
    public Completable save(Product product) {
        return repository.save(product);
    }

    @Override
    public Completable saveProductList(List<Product> products) {
        return repository.saveProductList(products);
    }

    @Override
    public Completable remove(String productId) {
        return repository.remove(productId);
    }

    @Override
    public Completable removeAll() {
        return repository.removeAll();
    }

    @Override
    public Single<Product> getProduct(String productId) {
        return repository.getProduct(productId);
    }

    @Override
    public Single<List<Product>> getProducts() {
        return repository.getProducts();
    }

    @Override
    public Single<List<Product>> getShopProductList() {
        return shop.getShopProductList();
    }
}
