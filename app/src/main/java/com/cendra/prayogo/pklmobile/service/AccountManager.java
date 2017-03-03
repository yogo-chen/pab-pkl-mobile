package com.cendra.prayogo.pklmobile.service;

import android.os.AsyncTask;

/**
 * Created by Admin on 3/2/2017.
 */

public class AccountManager {
    private AccountManager() {
    }

    public static void register(final AccountManager.OnEventListener onEventListener, final String email, final String birthday, final String name, final String address, final String phone, final String featuredProduct) {
        AsyncTask<Void, Void, String> asyncTask = new AsyncTask<Void, Void, String>() {
            @Override
            protected void onPreExecute() {
                onEventListener.onPreTask();
            }

            @Override
            protected String doInBackground(Void... params) {
                return PklWsdlUtils.registerPkl(email, birthday, name, address, phone, featuredProduct);
            }

            @Override
            protected void onPostExecute(String s) {
                if (s.equals("SERVER_ERROR")) {
                    onEventListener.onServerError();
                } else if (s.equals("CONNECTION_ERROR")) {
                    onEventListener.onConnectionError();
                } else if (s.equals("PARSE_ERROR")) {
                    // ignored since impossible
                } else if (s.matches("^\\(\\\"sukses\\\"\\,\\\"(.)+\\\"\\,\\\"didaftarkan\\\"\\)$")) {
                    onEventListener.onResultSuccess();
                } else {
                    onEventListener.onResultFailed();
                }
            }
        };
        asyncTask.execute();
    }

    public interface OnEventListener {
        public abstract void onPreTask();

        public abstract void onResultSuccess();

        public abstract void onResultFailed();

        public abstract void onServerError();

        public abstract void onConnectionError();
    }
}
