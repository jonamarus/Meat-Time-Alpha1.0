package com.example.android.meat_timealpha10.RestService;

import android.text.TextUtils;

import com.example.android.meat_timealpha10.helpers.HelperMethods;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import interceptors.AuthenticationInterceptor;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by faby on 27/06/17.
 */

public class RestClient {

  private static final String BASE_URL = "http://192.168.1.81:3000/";

  private static Retrofit.Builder builder =
    new Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(GsonConverterFactory.create());

  private static Retrofit retrofit = builder.build();

  private static HttpLoggingInterceptor logging =
    new HttpLoggingInterceptor()
      .setLevel(HttpLoggingInterceptor.Level.BODY);

  private static OkHttpClient.Builder httpClient =
    new OkHttpClient.Builder();

  public static <S> S createService(Class<S> serviceClass) {
    return createService(serviceClass, "");
  }

  public static <S> S createService(
    Class<S> serviceClass, final String token) {
    if (!TextUtils.isEmpty(token)) {
      AuthenticationInterceptor interceptor =
        new AuthenticationInterceptor(token);

      if (!httpClient.interceptors().contains(interceptor)) {
        httpClient.addInterceptor(interceptor);

        builder.client(httpClient.build());
        retrofit = builder.build();
      }
    }

    return retrofit.create(serviceClass);
  }

  /*public static Retrofit getClient(final String token) {
    if (retrofit==null) {

      HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
      logging.setLevel(HttpLoggingInterceptor.Level.BODY);

      OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

      httpClient.addInterceptor(new Interceptor() {
        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
          Request original = chain.request();

          // Request customization: add request headers
          Request.Builder requestBuilder = original.newBuilder()
            .header("Authorization", token); // <-- this is the important line

          Request request = requestBuilder.build();
          return chain.proceed(request);
        }
      });
      //httpClient.addInterceptor(logging);

      Gson gson = new GsonBuilder()
              .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
              .create();

      retrofit = new Retrofit.Builder()
              .baseUrl(BASE_URL)
              .addConverterFactory(GsonConverterFactory.create(gson))
              .client(httpClient.build())
              .build();

    }
    return retrofit;
  }*/



}
