package com.stolbunov.roman.shoppinglist.ui.screens.store_assortment;

import com.stolbunov.roman.domain.entities.Product;
import com.stolbunov.roman.domain.use_cases.IShopServiceUseCase;
import com.stolbunov.roman.shoppinglist.utils.Logger;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class StoreAssortmentFragmentViewModel extends ViewModel {
    private final IShopServiceUseCase shop;
    private final CompositeDisposable composite = new CompositeDisposable();

    private final MutableLiveData<List<Product>> productLD = new MutableLiveData<>();

    @Inject
    public StoreAssortmentFragmentViewModel(IShopServiceUseCase shop) {
        this.shop = shop;
        getProducts();
    }

    private void getProducts() {
        composite.add(
                shop.getShopProductList()
                        .subscribeOn(Schedulers.io())
                        .subscribe(productLD::postValue, this::logger));
    }

    private void logger(Throwable throwable) {
        Logger.log(getClass().getSimpleName(), throwable);
    }

    public LiveData<List<Product>> getProductLD() {
        return productLD;
    }
}
