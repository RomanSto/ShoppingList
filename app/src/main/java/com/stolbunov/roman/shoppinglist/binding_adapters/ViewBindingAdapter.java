package com.stolbunov.roman.shoppinglist.binding_adapters;

import android.view.View;

import androidx.databinding.BindingAdapter;

public class ViewBindingAdapter {
    @BindingAdapter("isVisible")
    public static void setVisibility(View view, Boolean isVisible) {
        if (isVisible == null) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        }
    }

    @BindingAdapter("isEnabled")
    public static void setEnabled(View view, Boolean isVisible) {
        if (isVisible == null) {
            view.setEnabled(false);
        } else {
            view.setEnabled(isVisible);
        }
    }
}
