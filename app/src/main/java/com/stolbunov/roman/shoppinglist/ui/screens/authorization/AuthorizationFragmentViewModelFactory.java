package com.stolbunov.roman.shoppinglist.ui.screens.authorization;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class AuthorizationFragmentViewModelFactory implements ViewModelProvider.Factory {
    private final AuthorizationFragmentViewModel vm;

    @Inject
    public AuthorizationFragmentViewModelFactory(AuthorizationFragmentViewModel vm) {
        this.vm = vm;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) vm;
    }
}
