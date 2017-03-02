package com.cendra.prayogo.pklmobile.model;

/**
 * Created by Admin on 3/2/2017.
 */

public class Transaction {
    final String name;
    final int soldPrice;
    final int quantity;
    final String date;

    public Transaction(String name, int soldPrice, int quantity, String date) {
        this.name = name;
        this.soldPrice = soldPrice;
        this.quantity = quantity;
        this.date = date;
    }
}
