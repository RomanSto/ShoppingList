package com.stolbunov.roman.shoppinglist.ui.screens.shopping_list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stolbunov.roman.shoppinglist.R;
import com.stolbunov.roman.shoppinglist.databinding.FragmentShoppingListBinding;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.support.DaggerFragment;

public class ShoppingListFragment extends DaggerFragment implements ShoppingListAdapter.OnRemoveItemClickListener {
    private ShoppingListFragmentViewModel vm;

    @Inject
    ShoppingListFragmentViewModelFactory factory;

    @Inject
    ShoppingListAdapter adapter;

    @Inject
    SwipeRemoveItemCallback callback;

    public static ShoppingListFragment newInstance() {
        return new ShoppingListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vm = ViewModelProviders
                .of(this, factory)
                .get(ShoppingListFragmentViewModel.class);

        FragmentShoppingListBinding binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_shopping_list,
                container,
                false);

        binding.setLifecycleOwner(this);
        binding.setVm(vm);
        binding.setItemTouchHelperCallback(callback);
        binding.soppingList.setAdapter(adapter);
        adapter.setListener(this);

        return binding.getRoot();
    }

    @Override
    public void removeClick(String productId) {
        vm.removeProduct(productId);
    }
}
