package com.stolbunov.roman.shoppinglist.ui.screens.shopping_list;

import com.stolbunov.roman.domain.entities.Product;
import com.stolbunov.roman.domain.use_cases.IUserShoppingUseCase;
import com.stolbunov.roman.shoppinglist.utils.Logger;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ShoppingListFragmentViewModel extends ViewModel {
    private final IUserShoppingUseCase useCase;
    private final CompositeDisposable composite = new CompositeDisposable();

    private final MutableLiveData<List<Product>> shoppingLD = new MutableLiveData<>();

    @Inject
    public ShoppingListFragmentViewModel(IUserShoppingUseCase useCase) {
        this.useCase = useCase;

        getShoppingList();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        composite.dispose();
    }

    public LiveData<List<Product>> getShoppingLD() {
        return shoppingLD;
    }

    public void removeProduct(String productId) {
        composite.add(useCase.remove(productId)
                .subscribeOn(Schedulers.io())
                .subscribe(this::getShoppingList, this::logger));
    }

    private void getShoppingList() {
        composite.add(useCase.getProducts()
                .subscribeOn(Schedulers.io())
                .subscribe(shoppingLD::postValue, this::logger));
    }

    private void logger(Throwable throwable) {
        Logger.log(getClass().getSimpleName(), throwable);
    }
}
