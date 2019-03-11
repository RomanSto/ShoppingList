package com.stolbunov.roman.shoppinglist.binding_adapters;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;

public class TextViewBindingAdapter {
    @BindingAdapter(value = {"textFormat", "dataFormat"}, requireAll = false)
    public static void setText(AppCompatTextView textView, String text, String data) {
        if (data != null) {
            if (text != null) {
                textView.setText(String.format(text, data));
            } else {
                textView.setText(data);
            }
        }
    }

}
