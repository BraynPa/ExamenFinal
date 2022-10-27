package com.example.vj20222.services;

import com.example.vj20222.entities.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UsersService {

    @GET("users")
    Call<List<Users>> all();
}
