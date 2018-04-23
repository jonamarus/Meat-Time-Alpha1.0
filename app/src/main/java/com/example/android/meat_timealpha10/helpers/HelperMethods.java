package com.example.android.meat_timealpha10.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.R;

/**
 * Created by faby on 02.02.18.
 */

public class HelperMethods {

  /**
   * Kijkt of er een token aanwezig is, dus of er ingelogd is of niet.
   * @param context de context van de applicatie
   * @return true als er een token bestaat, dus ingelogd is.
   */
  public static boolean isLoggedIn(Context context){
    boolean isLoggedIn;
    String token = TokenHelper.getToken(context);
    isLoggedIn = !token.equals("No token");

    //TODO: nakijken of token nog geldig is

    return isLoggedIn;
  }

  public static boolean isNetworkAvailable(Context context) {
    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
    return (activeNetwork != null) && activeNetwork.isConnectedOrConnecting();
  }
}
