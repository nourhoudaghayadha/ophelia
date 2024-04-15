package com.example.opheeeeeeeelia.Retrofit;

import com.example.opheeeeeeeelia.Model.LoginResult;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitInterface {
    @POST("/login")
    Call<LoginResult> executeLogin(@Body HashMap<String, String> map);

    @POST("/register")
    Call<Void> executeSignup (@Body HashMap<String, String> map);
}
