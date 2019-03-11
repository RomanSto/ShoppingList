package com.stolbunov.roman.shoppinglist.ui.screens.shopping_list;

import com.stolbunov.roman.domain.use_cases.IAuthUseCase;
import com.stolbunov.roman.domain.use_cases.IUserShoppingUseCase;
import com.stolbunov.roman.shoppinglist.entities.Event;
import com.stolbunov.roman.shoppinglist.entities.Nothing;
import com.stolbunov.roman.shoppinglist.utils.Logger;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ShoppingListActivityViewModel extends ViewModel {
    private final IUserShoppingUseCase userShoppingRepository;
    private final IAuthUseCase auth;
    private final CompositeDisposable composite = new CompositeDisposable();
    private final MutableLiveData<Event<Nothing>> isSuccessClearListLD = new MutableLiveData<>();

    @Override
    protected void onCleared() {
        super.onCleared();
        composite.dispose();
    }

    @Inject
    public ShoppingListActivityViewModel(IUserShoppingUseCase userShoppingRepository, IAuthUseCase auth) {
        this.userShoppingRepository = userShoppingRepository;
        this.auth = auth;
    }

    void clearList() {
        composite.add(userShoppingRepository.removeAll()
                .subscribeOn(Schedulers.io())
                .subscribe(() -> isSuccessClearListLD.postValue(new Event<>(new Nothing())), this::logger));
    }

    void signOut() {
        auth.signOut();
    }

    private void logger(Throwable throwable) {
        Logger.log(getClass().getSimpleName(), throwable);

    }

    public LiveData<Event<Nothing>> getIsSuccessClearListLD() {
        return isSuccessClearListLD;
    }
}
