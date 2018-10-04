package com.knights.blockathonapp;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by User on 04-Oct-18.
 */

public class DoctorsActivity extends AppCompatActivity {

    ProgressDialog progressBar;
    ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_activity);
        Log.e("hi","hi");
        progressBar=new ProgressDialog(this);
        progressBar.setMessage("Doctors loading..");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.show();
        progressBar.setCancelable(false);
        getDoctors();
        getRecord();

    }
    void getRecord()
    {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);
        SharedPreferences pref=getSharedPreferences("MyPref",MODE_PRIVATE);
        String id=pref.getString("username","0");
        Toast.makeText(getApplicationContext(), "UID: "+id, Toast.LENGTH_SHORT).show();


        Call<List<RecordDocument>> call = api.getRecord(id);


        call.enqueue(new Callback<List<RecordDocument>>() {
            @Override
            public void onResponse(Call<List<RecordDocument>> call, Response<List<RecordDocument>> response) {

                ////Implement the firebase fetching
                List<RecordDocument> docs = response.body();
                Toast.makeText(getApplicationContext(), docs.get(0).recordID, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<RecordDocument>> call, Throwable t) {
                progressBar.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    void getDoctors()
    {
        listView=findViewById(R.id.doc_list);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<Doctor>> call = api.getDoctor();

        call.enqueue(new Callback<List<Doctor>>() {
            @Override
            public void onResponse(Call<List<Doctor>> call, Response<List<Doctor>> response) {
                progressBar.dismiss();
                List<Doctor> doctrs = response.body();
                ListviewAdapter adapter=new ListviewAdapter();
                adapter.setData(doctrs,DoctorsActivity.this);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Doctor>> call, Throwable t) {
                progressBar.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}
