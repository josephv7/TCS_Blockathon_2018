package com.knights.blockathonapp;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by User on 04-Oct-18.
 */

public interface Api
{
    String BASE_URL = "http://192.168.43.61:3000";       //192.168.43.61
    @GET("api/Doctor")
    Call<List<Doctor>> getDoctor();

    @GET("/api/queries/ListByPatient")
    Call<List<RecordDocument>> getRecord(@Query(value = "id",encoded = true) String id);

}