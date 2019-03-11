package com.stolbunov.roman.shoppinglist.ui.screens.shopping_list;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stolbunov.roman.domain.entities.Product;
import com.stolbunov.roman.shoppinglist.R;
import com.stolbunov.roman.shoppinglist.databinding.ItemShoppingListBinding;
import com.stolbunov.roman.shoppinglist.ui.screens.base.adapters.IProductAdapter;

import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ShoppingListViewHolder>
        implements IProductAdapter {
    private List<Product> data;
    private OnRemoveItemClickListener listener;

    public ShoppingListAdapter() {
        data = Collections.emptyList();
    }

    @NonNull
    @Override
    public ShoppingListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemShoppingListBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_shopping_list,
                parent,
                false);

        return new ShoppingListViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingListViewHolder holder, int position) {

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

    public void removeItem(int position) {
        if (listener != null) {
            listener.removeClick(data.get(position).getId());
        }
    }

    class ShoppingListViewHolder extends RecyclerView.ViewHolder {
        private ItemShoppingListBinding binding;

        ShoppingListViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            itemView.setOnClickListener(this::itemClick);
        }

        private void itemClick(View view) {
            View line = binding.line;
            if (line.getVisibility() == View.GONE) {
                view.setBackgroundTintList(ColorStateList.valueOf(view
                        .getContext()
                        .getResources()
                        .getColor(R.color.colorItemBackgroundBought)));
                line.setVisibility(View.VISIBLE);
            } else {
                view.setBackgroundTintList(ColorStateList.valueOf(view
                        .getContext()
                        .getResources()
                        .getColor(R.color.colorWhite)));
                line.setVisibility(View.GONE);
            }
        }

        void bind(Product product) {
            binding.setProduct(product);
        }
    }

    public void setListener(OnRemoveItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnRemoveItemClickListener {
        void removeClick(String productId);
    }

}
