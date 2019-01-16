package com.example.ingenierosergio.retrofi;

import com.example.ingenierosergio.retrofi.modelos.Catalogo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {

    public static final String BASE_URL="https://www.udacity.com/public-api/v0/";

    @GET("courses")
    Call<Catalogo> listaCatalogo();
}
