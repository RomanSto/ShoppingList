package com.stolbunov.roman.shoppinglist.ui.screens.store_assortment;

import com.stolbunov.roman.domain.entities.Product;
import com.stolbunov.roman.domain.use_cases.IUserShoppingUseCase;
import com.stolbunov.roman.shoppinglist.utils.Logger;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class StoreAssortmentActivityViewModel extends ViewModel {
    private IUserShoppingUseCase useCase;
    private final List<Product> selectedProduct;
    private final CompositeDisposable composite = new CompositeDisposable();
    private final MutableLiveData<Boolean> isSuccessfulSave = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isShowCreateOrderButton = new MutableLiveData<>();

    @Override
    protected void onCleared() {
        super.onCleared();
        composite.dispose();
    }

    @Inject
    public StoreAssortmentActivityViewModel(IUserShoppingUseCase useCase) {
        this.useCase = useCase;

        selectedProduct = new ArrayList<>();
    }

    void saveSelectedProducts() {
        composite.add(useCase.saveProductList(selectedProduct)
                .subscribeOn(Schedulers.io())
                .subscribe(() -> isSuccessfulSave.postValue(true), this::logger));
    }

    void addProduct(Product product) {
        selectedProduct.add(product);
        isShowCreateOrderButton.setValue(true);
    }

    void removeProduct(Product product) {
        selectedProduct.remove(product);
        isShowCreateOrderButton.setValue(selectedProduct.size() > 0);
    }

    private void logger(Throwable t) {
        Logger.log(getClass().getSimpleName(), t);
    }

    public LiveData<Boolean> getIsSuccessfulSave() {
        return isSuccessfulSave;
    }

    public LiveData<Boolean> getIsShowCreateOrderButton() {
        return isShowCreateOrderButton;
    }
}
