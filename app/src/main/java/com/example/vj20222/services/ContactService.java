package com.example.vj20222.services;

import com.example.vj20222.entities.Contact;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ContactService {

    @GET("Contact")
    Call<List<Contact>> all();
    @GET("Contact/{id}")
    Call<Contact> get(@Path("id") int id);
    @POST("Contact")
    Call<Void> create(@Body Contact contact);
    @PUT("Contact/{id}")
    Call<Contact> update(@Body Contact contact, @Path("id") int id);
    @DELETE("Contact/{id}")
    Call<Void> delete(@Path("id") int id);
}
