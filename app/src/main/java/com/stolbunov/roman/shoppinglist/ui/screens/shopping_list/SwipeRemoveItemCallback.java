package com.stolbunov.roman.shoppinglist.ui.screens.shopping_list;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class SwipeRemoveItemCallback extends ItemTouchHelper.SimpleCallback {
    private ShoppingListAdapter adapter;

    @Inject
    public SwipeRemoveItemCallback(ShoppingListAdapter adapter) {
        super(ItemTouchHelper.ACTION_STATE_IDLE, ItemTouchHelper.START | ItemTouchHelper.END);
        this.adapter = adapter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView,
                          @NonNull RecyclerView.ViewHolder viewHolder,
                          @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        adapter.removeItem(viewHolder.getAdapterPosition());
    }
}
