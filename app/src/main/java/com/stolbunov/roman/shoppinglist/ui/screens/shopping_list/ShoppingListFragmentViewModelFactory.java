package com.stolbunov.roman.shoppinglist.ui.screens.shopping_list;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ShoppingListFragmentViewModelFactory implements ViewModelProvider.Factory {
    private final ShoppingListFragmentViewModel vm;

    @Inject
    public ShoppingListFragmentViewModelFactory(ShoppingListFragmentViewModel vm) {
        this.vm = vm;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) vm;
    }
}
