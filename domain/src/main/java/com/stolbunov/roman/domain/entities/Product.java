package com.stolbunov.roman.domain.entities;

import java.util.Objects;
import java.util.UUID;

public class Product {
    private final String id;
    private final String name;
    private final String urlImage;
    private final String price;

    public Product(String name, String urlImage, String price) {
        this(UUID.randomUUID().toString(), name, urlImage, price);
    }

    public Product(String id, String name, String urlImage, String price) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(getId(), product.getId()) &&
                Objects.equals(getName(), product.getName()) &&
                Objects.equals(getUrlImage(), product.getUrlImage()) &&
                Objects.equals(getPrice(), product.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getUrlImage(), getPrice());
    }
}
