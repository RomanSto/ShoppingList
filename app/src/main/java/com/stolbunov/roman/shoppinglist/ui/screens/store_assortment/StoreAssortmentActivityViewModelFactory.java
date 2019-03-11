package com.stolbunov.roman.shoppinglist.ui.screens.store_assortment;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class StoreAssortmentActivityViewModelFactory implements ViewModelProvider.Factory {
    private StoreAssortmentActivityViewModel vm;

    @Inject
    public StoreAssortmentActivityViewModelFactory(StoreAssortmentActivityViewModel vm) {
        this.vm = vm;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) vm;
    }
}
