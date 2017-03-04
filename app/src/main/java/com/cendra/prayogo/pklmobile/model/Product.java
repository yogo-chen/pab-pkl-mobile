package com.cendra.prayogo.pklmobile.model;

/**
 * Created by Admin on 3/2/2017.
 */

public class Product {
    public final String name;
    public final int basePrice;
    public final int sellPrice;

    public Product(String name, int basePrice, int sellPrice) {
        this.name = name;
        this.basePrice = basePrice;
        this.sellPrice = sellPrice;
    }
}
