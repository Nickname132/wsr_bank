package com.example.worldskilsbank;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiService {
    @GET("daily_json.js")
    Call<ServerResponse> getAllCurrency();
}
