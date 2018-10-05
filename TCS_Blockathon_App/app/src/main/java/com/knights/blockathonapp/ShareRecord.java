package com.knights.blockathonapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShareRecord extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_record);
        SharedPreferences pref = getSharedPreferences("MyPref",MODE_PRIVATE);

        Intent doctorGetIntent = getIntent();
        String doctorId = doctorGetIntent.getStringExtra("doctorId");

        final String id = pref.getString("username","0");
        final String recordID = pref.getString("recordID","0");
        Log.d("hiii",id);


        Button btn=(Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Api.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                        .build();

                Api api = retrofit.create(Api.class);

                Call<List<DoctorPost>> call = api.setDoctor(id,"resource:org.example.basic.MedicalRecord#"+recordID);


                call.enqueue(new Callback<List<DoctorPost>>() {
                    @Override
                    public void onResponse(Call<List<DoctorPost>> caldl, Response<List<DoctorPost>> response) {

                        ////Implement the firebase fetching
                        Log.d("response",response.toString());
                        List<DoctorPost> docs = response.body();
                        SharedPreferences pref=getSharedPreferences("MyPref",MODE_PRIVATE);
                        SharedPreferences.Editor editpref=pref.edit();
                      //  editpref.putString("recordID",docs.get(0).doctorID);
                      //  editpref.putString("asset",docs.get(0).doctorID);
                        editpref.commit();

                        Toast.makeText(getApplicationContext(), docs.get(0).doctorID, Toast.LENGTH_SHORT).show();
//                Intent intent=new Intent(Home.this,DocumentFetch.class);
//                        homeIntent.putExtra("doc_id",docs.get(0).recordID);
  //                      Toast.makeText(Home.this, "User Record Fetched", Toast.LENGTH_SHORT).show();
//                startActivity(intent);
//                finish();


                    }

                    @Override
                    public void onFailure(Call<List<DoctorPost>> call, Throwable t) {

                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}
