package com.example.android.meat_timealpha10.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.R;
import android.util.Base64;
import android.util.Log;

import java.io.UnsupportedEncodingException;

/**
 * Created by faby on 02.02.18.
 */

public class HelperMethods {

  /**
   * Kijkt of er een token aanwezig is, dus of er ingelogd is of niet.
   *
   * @param context de context van de applicatie
   * @return true als er een token bestaat, dus ingelogd is.
   */
  public static boolean isLoggedIn(Context context) {
    boolean isLoggedIn;
    String jsonBody = "";
    String token = TokenHelper.getToken(context);
    isLoggedIn = !token.equals("No Token");

    try {
      jsonBody = HelperMethods.decoded(token);

    } catch (Exception e) {
      e.printStackTrace();
    }


    //TODO: nakijken of token nog geldig is

    return isLoggedIn;
  }

  public static boolean isNetworkAvailable(Context context) {
    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
    return (activeNetwork != null) && activeNetwork.isConnectedOrConnecting();
  }

  public static String decoded(String JWTEncoded) throws Exception {
    String body = "";
    try {
      String[] split = JWTEncoded.split("\\.");
      body = getJson(split[1]);
    } catch (UnsupportedEncodingException e) {
      //Error
    }

    return body;
  }

  private static String getJson(String strEncoded) throws UnsupportedEncodingException {
    byte[] decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE);
    return new String(decodedBytes, "UTF-8");
  }
}
