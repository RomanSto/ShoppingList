package com.stolbunov.roman.shoppinglist.ui.screens.store_assortment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.stolbunov.roman.domain.entities.Product;
import com.stolbunov.roman.shoppinglist.R;
import com.stolbunov.roman.shoppinglist.databinding.ActivityStoreAssortmentBinding;
import com.stolbunov.roman.shoppinglist.ui.screens.shopping_list.ShoppingListActivity;

import javax.inject.Inject;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.support.DaggerAppCompatActivity;

public class StoreAssortmentActivity extends DaggerAppCompatActivity
        implements StoreAssortmentFragment.OnChoiceProductListener {
    private StoreAssortmentActivityViewModel vm;

    @Inject
    StoreAssortmentActivityViewModelFactory factory;


    public static Intent getIntent(Context packageContext) {
        return new Intent(packageContext, StoreAssortmentActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        vm = ViewModelProviders
                .of(this, factory)
                .get(StoreAssortmentActivityViewModel.class);

        ActivityStoreAssortmentBinding binding = DataBindingUtil
                .setContentView(
                        this,
                        R.layout.activity_store_assortment);

        binding.setLifecycleOwner(this);
        binding.setVm(vm);

        binding.setLifecycleOwner(this);
        binding.createOrder.setOnClickListener(this::createOrder);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_shop_price, StoreAssortmentFragment.newInstance())
                .commit();

        subscriptions();
    }

    private void subscriptions() {
        vm.getIsSuccessfulSave().observe(this, this::showUserShopping);
    }

    private void showUserShopping(Boolean isSuccess) {
        if (isSuccess) {
            Intent intent = ShoppingListActivity.getIntent(this);
            startActivity(intent);
            finish();
        }
    }

    private void createOrder(View view) {
        vm.saveSelectedProducts();
    }

    @Override
    public void onChoiceProduct(Product product) {
        vm.addProduct(product);
    }

    @Override
    public void onCancelProduct(Product product) {
        vm.removeProduct(product);
    }
}
