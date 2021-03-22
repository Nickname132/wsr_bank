package com.example.worldskilsbank;
import retrofit2.Call;
import retrofit2.http.GET;
/**
 * Контроллер для получения курсов валют
 */
public interface CurrencyController {

    @GET("daily_json.js")
    Call<ServerResponse> getAllCurrency();
}
