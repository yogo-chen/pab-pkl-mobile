package com.cendra.prayogo.pklmobile.model;

/**
 * Created by Admin on 3/2/2017.
 */

public class User {
    final String email;
    final String birthday;
    final String name;
    final String address;
    final String phone;

    public User(String email, String birthday, String name, String address, String phone) {
        this.email = email;
        this.birthday = birthday;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

}
