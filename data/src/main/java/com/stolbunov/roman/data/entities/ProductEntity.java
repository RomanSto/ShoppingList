package com.stolbunov.roman.data.entities;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class ProductEntity {
    private String id;
    private String name;
    private String urlImage;
    private String price;

    public ProductEntity() {
    }

    public ProductEntity(String id, String name, String urlImage, String price) {
        this.id = id;
        this.name = name;
        this.urlImage = urlImage;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public String getPrice() {
        return price;
    }
}
