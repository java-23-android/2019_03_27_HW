package com.sheygam.java_23_27_03_19_hw;

import android.content.Context;

public class StoreProvider {
    public static final String AUTH = "AUTH";
    public static final String PROFILE = "PROFILE";

    public static final String TOKEN_KEY = "TOKEN";

    private static final StoreProvider ourInstance = new StoreProvider();
    private Context context;

    public static StoreProvider getInstance() {
        return ourInstance;
    }

    private StoreProvider() {
    }

    public void setContext(Context context){
        this.context = context;
    }

    public void login(String email, String password){
        context.getSharedPreferences(AUTH,Context.MODE_PRIVATE)
                .edit()
                .putString(TOKEN_KEY,email+"&"+password)
                .apply();
    }

    public String getToken(){
        return context.getSharedPreferences(AUTH,Context.MODE_PRIVATE)
                .getString(TOKEN_KEY,null);
    }

    public void logout(){
        context.getSharedPreferences(AUTH,Context.MODE_PRIVATE)
                .edit()
                .remove(TOKEN_KEY)
                .apply();
    }

    public Profile getProfile(){
        String str = context.getSharedPreferences(PROFILE,Context.MODE_PRIVATE)
                .getString(getToken(),null);
        if(str!=null){
            return Profile.fromString(str);
        }
        return null;
    }

    public void putProfile(Profile profile){
        context.getSharedPreferences(PROFILE,Context.MODE_PRIVATE)
                .edit()
                .putString(getToken(),profile.toString())
                .apply();
    }
}
