package com.stolbunov.roman.shoppinglist.ui.screens.shopping_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.stolbunov.roman.shoppinglist.R;
import com.stolbunov.roman.shoppinglist.databinding.ActivityShoppingListBinding;
import com.stolbunov.roman.shoppinglist.entities.Event;
import com.stolbunov.roman.shoppinglist.entities.Nothing;
import com.stolbunov.roman.shoppinglist.ui.screens.authorization.AuthorizationActivity;
import com.stolbunov.roman.shoppinglist.ui.screens.store_assortment.StoreAssortmentActivity;

import java.util.Collections;

import javax.inject.Inject;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.support.DaggerAppCompatActivity;

public class ShoppingListActivity extends DaggerAppCompatActivity {
    @Inject
    ShoppingListActivityViewModelFactory factory;
    private ShoppingListActivityViewModel vm;
    private ShoppingListFragment fragment;

    public static Intent getIntent(Context packageContext) {
        return new Intent(packageContext, ShoppingListActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViewModel();

        ActivityShoppingListBinding binding = DataBindingUtil
                .setContentView(
                        this,
                        R.layout.activity_shopping_list);

        setSupportActionBar(binding.bottomAppBar);
        binding.setLifecycleOwner(this);

        binding.showStoreList.setOnClickListener(this::showStoreAssortment);

        fragment = ShoppingListFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_shopping_list_fragment,
                        fragment)
                .commit();

        subscriptions();
    }

    private void subscriptions() {
        vm.getIsSuccessClearListLD().observe(this, this::successClearList);
    }

    private void initViewModel() {
        vm = ViewModelProviders
                .of(this, factory)
                .get(ShoppingListActivityViewModel.class);
    }

    private void showStoreAssortment(View view) {
        startActivity(StoreAssortmentActivity.getIntent(this));
    }

    private void successClearList(Event<Nothing> nothingEvent) {
        if (fragment != null) {
            if (!nothingEvent.isHandled()) {
                fragment.adapter.setData(Collections.emptyList());
                fragment.adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_shopping_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clear_list:
                vm.clearList();
                return true;
            case R.id.sing_out:
                vm.signOut();
                startActivity(AuthorizationActivity.getIntent(this));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
