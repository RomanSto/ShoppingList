package com.stolbunov.roman.shoppinglist.ui.screens.store_assortment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stolbunov.roman.domain.entities.Product;
import com.stolbunov.roman.shoppinglist.R;
import com.stolbunov.roman.shoppinglist.databinding.FragmentStoreAssortmentBinding;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import dagger.android.support.DaggerFragment;

public class StoreAssortmentFragment extends DaggerFragment
        implements StoreAssortmentAdapter.OnChoiceProductListener {
    private static final int spanCount = 2;
    private StoreAssortmentFragmentViewModel vm;
    private FragmentStoreAssortmentBinding binding;
    private OnChoiceProductListener choiceProductListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        choiceProductListener = (OnChoiceProductListener) context;
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        choiceProductListener = (OnChoiceProductListener) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        choiceProductListener = null;
    }

    @Inject
    StoreAssortmentAdapter adapter;

    @Inject
    StoreAssortmentFragmentViewModelFactory factory;

    public static StoreAssortmentFragment newInstance() {
        return new StoreAssortmentFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        initViewModule();
        initDataBinding(inflater, container);
        initListeners();

        return binding.getRoot();
    }

    private void initDataBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_store_assortment,
                container,
                false);

        binding.shopPriceRv.setAdapter(adapter);
        binding.shopPriceRv.setLayoutManager(new GridLayoutManager(getActivity(), spanCount));
        binding.setLifecycleOwner(this);
        binding.setVm(vm);
    }

    private void initListeners() {
        adapter.setChoiceProductListener(this);
    }

    private void initViewModule() {
        vm = ViewModelProviders
                .of(this, factory)
                .get(StoreAssortmentFragmentViewModel.class);
    }

    @Override
    public void onChoiceProduct(Product product) {
        if (choiceProductListener != null) {
            choiceProductListener.onChoiceProduct(product);
        }
    }

    @Override
    public void onCancelProduct(Product product) {
        if (choiceProductListener != null) {
            choiceProductListener.onCancelProduct(product);
        }
    }

    public interface OnChoiceProductListener {
        void onChoiceProduct(Product product);

        void onCancelProduct(Product product);
    }
}
