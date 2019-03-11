package com.stolbunov.roman.shoppinglist.binding_adapters;

import com.stolbunov.roman.domain.entities.Product;
import com.stolbunov.roman.shoppinglist.ui.screens.base.adapters.IProductAdapter;

import java.util.List;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewBindingAdapter {

    @BindingAdapter("data")
    public static void setData(RecyclerView recyclerView, List<Product> products) {
        IProductAdapter adapter = ((IProductAdapter) recyclerView.getAdapter());

        if (adapter != null && products != null) {
            adapter.setData(products);
        }
    }

    @BindingAdapter("itemTouchHelperCallback")
    public static void setItemTouchHelperCallback(RecyclerView recyclerView,
                                                  ItemTouchHelper.SimpleCallback callback) {
        if (callback != null) {
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
            itemTouchHelper.attachToRecyclerView(recyclerView);
        }
    }
}
