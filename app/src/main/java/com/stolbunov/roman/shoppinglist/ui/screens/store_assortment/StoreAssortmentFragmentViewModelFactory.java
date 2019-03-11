package com.stolbunov.roman.shoppinglist.ui.screens.store_assortment;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class StoreAssortmentFragmentViewModelFactory implements ViewModelProvider.Factory {
    private StoreAssortmentFragmentViewModel vm;

    @Inject
    public StoreAssortmentFragmentViewModelFactory(StoreAssortmentFragmentViewModel vm) {
        this.vm = vm;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) vm;
    }
}
