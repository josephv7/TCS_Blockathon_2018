package com.knights.blockathonapp;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by User on 04-Oct-18.
 */

public interface Api
{
    String BASE_URL = "http://192.168.43.61:3000/";       //192.168.43.61
    @GET("api/Doctor")
    Call<List<Doctor>> getDoctor();
}