package com.stolbunov.roman.shoppinglist.di;

import com.stolbunov.roman.shoppinglist.di.scope.ActivityScope;
import com.stolbunov.roman.shoppinglist.di.scope.FragmentScope;
import com.stolbunov.roman.shoppinglist.ui.screens.authorization.AuthorizationActivity;
import com.stolbunov.roman.shoppinglist.ui.screens.authorization.AuthorizationFragment;
import com.stolbunov.roman.shoppinglist.ui.screens.shopping_list.ShoppingListActivity;
import com.stolbunov.roman.shoppinglist.ui.screens.shopping_list.ShoppingListFragment;
import com.stolbunov.roman.shoppinglist.ui.screens.store_assortment.StoreAssortmentActivity;
import com.stolbunov.roman.shoppinglist.ui.screens.store_assortment.StoreAssortmentFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Module(includes = AndroidSupportInjectionModule.class)
public interface DaggerAndroidModule {

    @ActivityScope
    @ContributesAndroidInjector
    AuthorizationActivity authorizationActivity();

    @ActivityScope
    @ContributesAndroidInjector
    ShoppingListActivity shoppingListActivity();

    @ActivityScope
    @ContributesAndroidInjector
    StoreAssortmentActivity shopActivity();

    @FragmentScope
    @ContributesAndroidInjector
    AuthorizationFragment authorizationFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = FragmentModule.class)
    ShoppingListFragment shoppingListFragment();

    @FragmentScope
    @ContributesAndroidInjector
    StoreAssortmentFragment shopFragment();
}
