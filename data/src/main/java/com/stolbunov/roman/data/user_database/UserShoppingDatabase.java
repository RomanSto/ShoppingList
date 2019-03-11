package com.stolbunov.roman.data.user_database;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.stolbunov.roman.data.mapper.UserShoppingMapper;
import com.stolbunov.roman.data.utils.RxFirebase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

public class UserShoppingDatabase {
    private static final String PATH_TO_PRODUCT = "/shopping/%s/products/%s";
    private static final String PATH_TO_PRODUCTS = "/shopping/%s/products";

    @Inject
    public UserShoppingDatabase() {
    }

    public Completable save(Map<String, Object> entityMap) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        return Completable.fromAction(() -> addProductEntity(entityMap, user, reference));
    }

    public Completable saveProductList(List<Map<String, Object>> entityMapList) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        return Completable.fromAction(() -> addProductEntityList(entityMapList, user, reference));
    }

    public Completable remove(String productId) {
        return Completable.fromAction(() -> removeProductById(productId));
    }

    public Completable removeAll() {
        return Completable.fromAction(this::removeAllProducts);
    }

    public Single<DataSnapshot> getDataSnapshotForProductEntity(String productId) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        return RxFirebase.singleValueEventObserver(
                FirebaseDatabase
                        .getInstance()
                        .getReference(String.format(PATH_TO_PRODUCT, user.getUid(), productId)));
    }

    public Single<DataSnapshot> getDataSnapshotForProductEntities() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        return RxFirebase.singleValueEventObserver(
                FirebaseDatabase
                        .getInstance()
                        .getReference(String.format(PATH_TO_PRODUCTS, user.getUid())));
    }

    private void addProductEntity(Map<String, Object> entityMap, FirebaseUser user, DatabaseReference reference) {
        Map<String, Object> result = new HashMap<>();
        result.put(String.format(PATH_TO_PRODUCT,
                user.getUid(),
                entityMap.get(UserShoppingMapper.KEY_PRODUCT_ID)),
                entityMap);
        reference.updateChildren(result);
    }

    private void addProductEntityList(List<Map<String, Object>> entityMapList,
                                      FirebaseUser user, DatabaseReference reference) {
        for (Map<String, Object> entityList : entityMapList) {
            addProductEntity(entityList, user, reference);
        }
    }

    private void removeProductById(String productId) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase
                .getInstance()
                .getReference(String.format(PATH_TO_PRODUCT, user.getUid(), productId))
                .removeValue();
    }

    private void removeAllProducts() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase
                .getInstance()
                .getReference(String.format(PATH_TO_PRODUCTS, user.getUid()))
                .removeValue();
    }
}
