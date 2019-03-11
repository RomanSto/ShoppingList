package com.stolbunov.roman.shoppinglist.ui.screens.base.adapters;

import com.stolbunov.roman.domain.entities.Product;

import java.util.List;

public interface IProductAdapter {
    void setData(List<Product> data);
}
