package com.cendra.prayogo.pklmobile.service;

import android.os.AsyncTask;

/**
 * Created by Admin on 3/2/2017.
 */

public class AccountManager {
    private AccountManager() {
    }

    public static void register(final Handler handler, final String email, final String birthday, final String name, final String address, final String phone, final String featuredProduct) {
        AsyncTask<Void, Void, String> asyncTask = new AsyncTask<Void, Void, String>() {
            @Override
            protected void onPreExecute() {
                handler.onPreTask();
            }

            @Override
            protected String doInBackground(Void... params) {
                return PklWsdlUtils.registerPkl(email, birthday, name, address, phone, featuredProduct);
            }

            @Override
            protected void onPostExecute(String s) {
                handler.onTaskResult(s);
            }
        };
        asyncTask.execute();
    }

    public interface Handler {
        public abstract void onPreTask();

        public abstract void onTaskResult(String res);
    }
}
