package com.stolbunov.roman.shoppinglist.di;

import com.stolbunov.roman.shoppinglist.di.scope.FragmentScope;
import com.stolbunov.roman.shoppinglist.ui.screens.shopping_list.ShoppingListAdapter;

import dagger.Module;
import dagger.Provides;

@Module
class FragmentModule {

    @FragmentScope
    @Provides
    ShoppingListAdapter provideShoppingListAdapter() {
        return new ShoppingListAdapter();
    }
}
