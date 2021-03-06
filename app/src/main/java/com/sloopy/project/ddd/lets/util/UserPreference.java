package com.sloopy.project.ddd.lets.util;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreference {

    public void saveUserProfile(Context context, String id, String name, String email, String photo) {
        SharedPreferences pref = context.getSharedPreferences("userProfile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("id", id);
        editor.putString("name", name);
        editor.putString("email", email);
        editor.putString("photo", photo);
        editor.apply();
    }

    public void saveDogProfile(Context context, String id, String name, String gender, String birth) {
        SharedPreferences pref = context.getSharedPreferences("dogProfile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("userId", id);
        editor.putString("name", name);
        editor.putString("gender", gender);
        editor.putString("birth", birth);
        editor.apply();
    }
}
