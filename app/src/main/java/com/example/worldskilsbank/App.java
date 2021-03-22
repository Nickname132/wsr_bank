package com.example.worldskilsbank;

import androidx.multidex.MultiDexApplication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.worldskilsbank.Settings.SERVER_URL;

public class App extends MultiDexApplication {

    private Retrofit retrofit;

    private static CurrencyController currencyController;

    public static CurrencyController getCurrencyController() {
        return currencyController;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Gson gson = new GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        currencyController = retrofit.create(CurrencyController.class);
    }
}