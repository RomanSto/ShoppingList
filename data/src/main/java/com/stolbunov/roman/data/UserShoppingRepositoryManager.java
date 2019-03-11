package com.stolbunov.roman.data;

import com.stolbunov.roman.data.mapper.ShopServiceMapper;
import com.stolbunov.roman.data.mapper.UserAuthorizationMapper;
import com.stolbunov.roman.data.mapper.UserShoppingMapper;
import com.stolbunov.roman.data.shop_database.ShopService;
import com.stolbunov.roman.data.user_database.AuthorizationRepository;
import com.stolbunov.roman.data.user_database.UserShoppingDatabase;
import com.stolbunov.roman.domain.entities.Credentials;
import com.stolbunov.roman.domain.entities.Product;
import com.stolbunov.roman.domain.repository.IAuthorizationRepository;
import com.stolbunov.roman.domain.repository.IShopServiceRepository;
import com.stolbunov.roman.domain.repository.IUserShoppingRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

public class UserShoppingRepositoryManager implements IAuthorizationRepository,
        IUserShoppingRepository, IShopServiceRepository {
    private final AuthorizationRepository auth;
    private final UserShoppingDatabase database;
    private final ShopService shopService;

    @Inject
    public UserShoppingRepositoryManager(AuthorizationRepository auth, UserShoppingDatabase database,
                                         ShopService shopService) {
        this.auth = auth;
        this.database = database;
        this.shopService = shopService;
    }

    @Override
    public Completable createUserWithEmailAndPassword(Credentials credentials) {
        return Single.just(credentials)
                .map(UserAuthorizationMapper::transformCredentialsToCredentialsEntity)
                .flatMapCompletable(auth::createUserWithEmailAndPassword);
    }

    @Override
    public Completable signInWithEmailAndPassword(Credentials credentials) {
        return Single.just(credentials)
                .map(UserAuthorizationMapper::transformCredentialsToCredentialsEntity)
                .flatMapCompletable(auth::signInWithEmailAndPassword);
    }

    @Override
    public void signOut() {
        auth.signOut();
    }

    @Override
    public boolean isCurrentUser() {
        return auth.isCurrentUser();
    }

    @Override
    public Completable save(Product product) {
        return Single.just(product)
                .map(UserShoppingMapper::transformProductToMap)
                .flatMapCompletable(database::save);
    }

    @Override
    public Completable saveProductList(List<Product> products) {
        return Single.just(products)
                .map(UserShoppingMapper::transformProductListToMap)
                .flatMapCompletable(database::saveProductList);
    }

    @Override
    public Completable remove(String productId) {
        return Single.just(productId)
                .flatMapCompletable(database::remove);
    }

    @Override
    public Completable removeAll() {
        return database.removeAll();
    }

    @Override
    public Single<Product> getProduct(String productId) {
        return database.getDataSnapshotForProductEntity(productId)
                .map(UserShoppingMapper::transformDataSnapshotToProduct);
    }

    @Override
    public Single<List<Product>> getProducts() {
        return database.getDataSnapshotForProductEntities()
                .map(UserShoppingMapper::transformDataSnapshotToProducts);
    }

    @Override
    public Single<List<Product>> getShopProductList() {
        return Single.fromCallable(shopService::getProductList)
                .map(ShopServiceMapper::transformToProductList);
    }
}
