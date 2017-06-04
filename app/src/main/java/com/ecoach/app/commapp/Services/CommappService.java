package com.ecoach.app.commapp.Services;

import com.ecoach.app.commapp.Models.Account;
import com.ecoach.app.commapp.Models.ResponseObjects.AccountOpeningResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

/**
 * Created by David on 5/23/2017.
 */

public interface  CommappService {
    @Headers("apikey: k894gw22d90b415793fa30a74bdfct78")
    @FormUrlEncoded
    @POST("/users/request_opening")
    Call<AccountOpeningResponse> requestOpening(@Field("first_name") String firstName, @Field("last_name") String lastName, @Field("gender") String gender, @Field("email") String email, @Field("phone_number") String phoneNumber, @Field("dob") String dob, @Field("account_type") String acountType, @Field("address") String address, @Field("institution_code") String institution_code);

    @Headers("apikey: k894gw22d90b415793fa30a74bdfct78")
    @FormUrlEncoded
    @POST("/users")
    Call<AccountOpeningResponse> loginUser(@Field("pin") String pin);

    @Headers("apikey: k894gw22d90b415793fa30a74bdfct78")
    @FormUrlEncoded
    @POST("/users")
    Call<AccountOpeningResponse> getEvents(@Field("pin") String code);

}
