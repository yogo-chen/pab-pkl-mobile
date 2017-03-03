package com.cendra.prayogo.pklmobile.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.cendra.prayogo.pklmobile.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountManager {
    private static final String PREF_NAME = "PKL_ACCOUNT";
    private static final String SID = "SID";
    private static final String EMAIL = "EMAIL";
    private static final String BIRTHDAY = "BIRTHDAY";
    private static final String NAME = "NAME";
    private static final String ADDRESS = "ADDRESS";
    private static final String PHONE = "PHONE";
    private static final String FEATURED_PRODUCT = "FEATURED_PRODUCT";

    private static User loggedInUser;

    private AccountManager() {
    }

    public static void register(final AccountManager.OnEventListener onEventListener, final User user) {
        AsyncTask<Void, Void, String> asyncTask = new AsyncTask<Void, Void, String>() {
            @Override
            protected void onPreExecute() {
                onEventListener.onPreTask();
            }

            @Override
            protected String doInBackground(Void... params) {
                return PklWsdlUtils.registerPkl(user.email, user.birthday, user.name, user.address, user.phone, user.featuredProduct);
            }

            @Override
            protected void onPostExecute(String s) {
                if (s.equals("SERVER_ERROR")) {
                    onEventListener.onServerError();
                } else if (s.equals("CONNECTION_ERROR")) {
                    onEventListener.onConnectionError();
                } else if (s.equals("PARSE_ERROR")) {
                    // ignored since impossible
                } else if (s.matches("^\\(\"sukses\",\"(.+)\",\"didaftarkan\"\\)$")) {
                    onEventListener.onResultSuccess();
                } else {
                    onEventListener.onResultFailed();
                }
            }
        };
        asyncTask.execute();
    }

    public static void login(final Context context, final AccountManager.OnEventListener onEventListener, final String email, final String birthday) {
        AsyncTask<Void, Void, String> asyncTaskLogin = new AsyncTask<Void, Void, String>() {
            @Override
            protected void onPreExecute() {
                onEventListener.onPreTask();
            }

            @Override
            protected String doInBackground(Void... params) {
                return PklWsdlUtils.login(email, birthday);
            }

            @Override
            protected void onPostExecute(String loginResultString) {
                if (loginResultString.equals("SERVER_ERROR")) {
                    onEventListener.onServerError();
                } else if (loginResultString.equals("CONNECTION_ERROR")) {
                    onEventListener.onConnectionError();
                } else if (loginResultString.equals("PARSE_ERROR")) {
                    // ignored since impossible
                } else {
                    Pattern p = Pattern.compile("^\\(\"OK\",\"(.+)\"\\)$");
                    Matcher m = p.matcher(loginResultString);
                    if (m.find()) {
                        // login approved
                        // get returned sid
                        final String sid = m.group(1);

                        // get user detail
                        AsyncTask<Void, Void, String> asyncTaskGetPkl = new AsyncTask<Void, Void, String>() {

                            @Override
                            protected String doInBackground(Void... params) {
                                return PklWsdlUtils.getPkl(sid);
                            }

                            @Override
                            protected void onPostExecute(String getPklResultString) {
                                if (getPklResultString.equals("SERVER_ERROR")) {
                                    onEventListener.onServerError();
                                } else if (getPklResultString.equals("CONNECTION_ERROR")) {
                                    onEventListener.onConnectionError();
                                } else if (getPklResultString.equals("PARSE_ERROR")) {
                                    // ignored since impossible
                                } else {
                                    Pattern p = Pattern.compile("^\\(\"(.+)\",\"(.+)\",\"(.+)\",\"(.+)\",\"(.+)\",\"(.+)\"\\)$");
                                    Matcher m = p.matcher(getPklResultString);
                                    if (m.find()) {
                                        String userEmail = m.group(1);
                                        String userName = m.group(2);
                                        String userAddress = m.group(3);
                                        String userPhone = m.group(4);
                                        String userBirthday = m.group(5);
                                        String userFeaturedProduct = m.group(6);

                                        // store sid and user detail
                                        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
                                        SharedPreferences.Editor ed = pref.edit();
                                        ed.putString(SID, sid);
                                        ed.putString(EMAIL, userEmail);
                                        ed.putString(BIRTHDAY, userBirthday);
                                        ed.putString(NAME, userName);
                                        ed.putString(ADDRESS, userAddress);
                                        ed.putString(PHONE, userPhone);
                                        ed.putString(FEATURED_PRODUCT, userFeaturedProduct);
                                        ed.apply();

                                        loggedInUser = new User(sid, userEmail, userBirthday, userName, userAddress, userPhone, userFeaturedProduct);

                                        onEventListener.onResultSuccess();
                                    } else {
                                        onEventListener.onResultFailed();
                                    }
                                }
                            }
                        };
                        asyncTaskGetPkl.execute();
                    } else {
                        onEventListener.onResultFailed();
                    }
                }
            }
        };
        asyncTaskLogin.execute();
    }

    public static void logout(Context context) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = pref.edit();
        ed.remove(SID);
        ed.remove(EMAIL);
        ed.remove(BIRTHDAY);
        ed.remove(NAME);
        ed.remove(ADDRESS);
        ed.remove(PHONE);
        ed.remove(FEATURED_PRODUCT);
        ed.apply();

        loggedInUser = null;
    }

    public static User getUser(Context context) {
        return loggedInUser;
    }

    public interface OnEventListener {
        void onPreTask();

        void onResultSuccess();

        void onResultFailed();

        void onServerError();

        void onConnectionError();
    }
}
