package com.example.android.meat_timealpha10.helpers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.android.meat_timealpha10.Activities.MainActivity;

/**
 * Created by faby on 18/10/17.
 */

public class TokenHelper {

  public static void setToken(String token, Context context) {
    SharedPreferences.Editor editor = context.getSharedPreferences("MeatTime", 0).edit();
    editor.putString("token", token);
    editor.apply();
  }

  public static String getToken(Context context) {
    SharedPreferences sharedPrefs = context.getSharedPreferences("MeatTime", 0);
    return sharedPrefs.getString("token", "No Token");
  }

  public static void logout(Context context){
    SharedPreferences.Editor editor  = context.getSharedPreferences("MeatTime", 0).edit();
    editor.remove("token");
    editor.apply();
  }

}
