package com.knights.blockathonapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by User on 04-Oct-18.
 */

public interface Api
{

    String BASE_URL = "http://localhost:3000/api/Doctor";
    @GET("/v1/doctor/5")
    Call<List<Doctor>> getDoctor();

}
