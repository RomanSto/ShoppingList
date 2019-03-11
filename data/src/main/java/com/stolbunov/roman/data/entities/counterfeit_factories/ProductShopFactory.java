package com.stolbunov.roman.data.entities.counterfeit_factories;

import com.stolbunov.roman.data.entities.ProductShop;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductShopFactory {
    private static List<ProductShop> products;

    static {
        products = new ArrayList<>();
        List<String> titles = createTitle();
        List<String> urlImages = createUrlImage();
        List<String> prices = createPrices();

        for (int i = 0; i < titles.size(); i++) {
            String id = UUID.randomUUID().toString();
            products.add(new ProductShop(
                    id,
                    titles.get(i),
                    urlImages.get(i),
                    prices.get(i)));
        }
    }

    public static List<ProductShop> getProductsShop() {
        return products;
    }

    private static List<String> createTitle() {
        List<String> title = new ArrayList<>();
        title.add("Apple iPhone Xs Max 64Gb Gold (MT522)");
        title.add("Huawei P Smart 2019 3/64Gb Aurora Blue (51093FTA)");
        title.add("Meizu 16 6/64Gb (Black)");
        title.add("Meizu X8 4/64Gb (Blue)");
        title.add("Samsung G950F Galaxy S8 2017 4/64Gb Midnight Black (SM-G950FZKDSEK)");
        title.add("Xiaomi Mi 8 Lite 4/64Gb (Midnight Black)");
        title.add("Meizu 16th 8/128Gb (Midnight Black)");
        title.add("Huawei P Smart Plus 2018 4/64Gb White (51093DYA)");
        title.add("Apple iPhone Xr 64Gb Black (MRY42)");
        title.add("Xiaomi Mi A2 Lite 3/32Gb (Black)");
        title.add("Huawei Y7 2019 3/32Gb Aurora Blue (51093HEU)");
        title.add("Xiaomi Mi A2 Lite 3/32Gb (Black)");
        title.add("Apple iPhone 8 64Gb Space Gray (MQ6G2)");
        title.add("Asus ZenFone Max M2 4/32Gb Silver (ZB633KL-4J072EU)");
        title.add("Xiaomi Redmi Note 5 3/32Gb (Black)");
        title.add("Meizu Pro 7 Plus 6/64GB (Black)");
        return title;
    }

    private static List<String> createUrlImage() {
        List<String> urlImage = new ArrayList<>();
        urlImage.add("https://i.citrus.ua/imgcache/size_800/uploads/shop/3/c/3ca7aabff2212d386690746e4f259df9.jpg");
        urlImage.add("https://i.citrus.ua/imgcache/size_800/uploads/shop/3/b/3b37bb6198c9fa097c189a1e22ea4e75.jpg");
        urlImage.add("https://i.citrus.ua/imgcache/size_800/uploads/shop/c/1/c10355ff93e92ae18ae1d0e008ea59c9.jpg");
        urlImage.add("https://i.citrus.ua/imgcache/size_800/uploads/shop/5/b/5b7511b9bbadfc6c85972afb3258b570.jpg");
        urlImage.add("https://i.citrus.ua/imgcache/size_800/uploads/shop/f/b/fb87ed6cea440efdc3d135e96daac698.jpg");
        urlImage.add("https://i.citrus.ua/imgcache/size_800/uploads/shop/4/9/49b64ebdfe21897e884e67daf5805e8a.jpg");
        urlImage.add("https://i.citrus.ua/imgcache/size_800/uploads/shop/f/d/fd85a112c60d9311650ad71506857a61.jpg");
        urlImage.add("https://i.citrus.ua/imgcache/size_800/uploads/shop/d/1/d1ae58369755602cb8855983c0188699.jpg");
        urlImage.add("https://i.citrus.ua/imgcache/size_800/uploads/shop/5/5/5533198c217ea9a4280fdf804df8f088.jpg");
        urlImage.add("https://i.citrus.ua/imgcache/size_800/uploads/shop/a/0/a0b78f5994bb3934ce551bdc6d75f10d.jpg");
        urlImage.add("https://i.citrus.ua/imgcache/size_800/uploads/shop/7/7/777cda0dd4f0f9253dee7ee78239d7ee.jpg");
        urlImage.add("https://i.citrus.ua/imgcache/size_800/uploads/shop/a/0/a0b78f5994bb3934ce551bdc6d75f10d.jpg");
        urlImage.add("https://i.citrus.ua/imgcache/size_800/uploads/shop/2/2/220a8d68c8b90a880d6b120350d3b094.jpg");
        urlImage.add("https://i.citrus.ua/imgcache/size_800/uploads/shop/1/7/1700785c41327c2d6e956fdf2705c35c.jpg");
        urlImage.add("https://i.citrus.ua/imgcache/size_800/uploads/shop/6/2/62f84ffab492670cba10e72e72ccb50c.jpg");
        urlImage.add("https://i.citrus.ua/imgcache/size_800/uploads/shop/8/b/8bbbea9bff97a372153fb8a44e75d134.jpg");
        return urlImage;
    }

    private static List<String> createPrices() {
        List<String> prices = new ArrayList<>();
        prices.add("34999");
        prices.add("5999");
        prices.add("7999");
        prices.add("6499");
        prices.add("14999");
        prices.add("6499");
        prices.add("12999");
        prices.add("6999");
        prices.add("24999");
        prices.add("4999");
        prices.add("5599");
        prices.add("4999");
        prices.add("19999");
        prices.add("5199");
        prices.add("4999");
        prices.add("6499");
        return prices;
    }
}
