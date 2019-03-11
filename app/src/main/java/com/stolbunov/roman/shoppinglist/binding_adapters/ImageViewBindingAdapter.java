package com.stolbunov.roman.shoppinglist.binding_adapters;

import com.bumptech.glide.Glide;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;

public class ImageViewBindingAdapter {

    @BindingAdapter("urlImage")
    public static void loadImage(AppCompatImageView imageView, String urlImage) {
        if (urlImage != null) {
            Glide.with(imageView.getContext())
                    .load(urlImage)
                    .into(imageView);
        }
    }
}
