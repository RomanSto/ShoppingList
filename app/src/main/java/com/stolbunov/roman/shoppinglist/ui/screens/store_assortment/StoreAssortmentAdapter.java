package com.stolbunov.roman.shoppinglist.ui.screens.store_assortment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stolbunov.roman.domain.entities.Product;
import com.stolbunov.roman.shoppinglist.R;
import com.stolbunov.roman.shoppinglist.databinding.ItemStoreAssortmentBinding;
import com.stolbunov.roman.shoppinglist.ui.screens.base.adapters.IProductAdapter;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class StoreAssortmentAdapter extends RecyclerView.Adapter<StoreAssortmentAdapter.ShopPriceViewHolder>
        implements IProductAdapter {
    private List<Product> data;
    private OnChoiceProductListener choiceProductListener;

    @Inject
    StoreAssortmentAdapter() {
        data = Collections.emptyList();
    }

    @NonNull
    @Override
    public ShopPriceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemStoreAssortmentBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_store_assortment,
                parent,
                false);

        return new ShopPriceViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ShopPriceViewHolder holder, int position) {

        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void setData(List<Product> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    class ShopPriceViewHolder extends RecyclerView.ViewHolder {
        private ItemStoreAssortmentBinding binding;

        ShopPriceViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        void bind(Product product) {
            binding.setProduct(product);

            binding.checkSelectedProduct.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (choiceProductListener != null) {
                    if (isChecked) {
                        choiceProductListener.onChoiceProduct(product);
                    } else {
                        choiceProductListener.onCancelProduct(product);
                    }
                }

            });
        }
    }

    public void setChoiceProductListener(OnChoiceProductListener choiceProductListener) {
        this.choiceProductListener = choiceProductListener;
    }

    public interface OnChoiceProductListener {
        void onChoiceProduct(Product product);

        void onCancelProduct(Product product);
    }
}
