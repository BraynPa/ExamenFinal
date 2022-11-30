package com.example.vj20222.services;

import com.example.vj20222.entities.Contact;
import com.example.vj20222.entities.Cuenta;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CuentaService {
    @GET("Cuenta")
    Call<List<Cuenta>> all();
    @GET("Cuenta/{id}")
    Call<Cuenta> get(@Path("id") int id);
    @POST("Cuenta")
    Call<Void> create(@Body Cuenta cuenta);
    @PUT("Cuenta/{id}")
    Call<Cuenta> update(@Body Cuenta cuenta, @Path("id") int id);
    @DELETE("Cuenta/{id}")
    Call<Void> delete(@Path("cuenta") int id);
}
