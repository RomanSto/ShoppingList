package com.stolbunov.roman.data.entities;

public class ProductShop {
    private final String id;
    private final String name;
    private final String urlImage;
    private final String price;

    public ProductShop(String id, String name, String urlImage, String price) {
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
