package com.cendra.prayogo.pklmobile.model;

/**
 * Created by Admin on 3/2/2017.
 */

public class Product {
    final String name;
    final int basePrice;
    final int sellPrice;

    public Product(String name, int basePrice, int sellPrice) {
        this.name = name;
        this.basePrice = basePrice;
        this.sellPrice = sellPrice;
    }
}
