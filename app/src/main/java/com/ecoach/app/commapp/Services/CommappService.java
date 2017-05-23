package com.ecoach.app.commapp.Services;

import com.ecoach.app.commapp.Models.Account;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by David on 5/23/2017.
 */

public interface  CommappService {
    @POST("users/new")
    Call<Account> createUser(@Body Account user);
}
