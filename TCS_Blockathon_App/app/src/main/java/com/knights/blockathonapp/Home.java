package com.knights.blockathonapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Home extends AppCompatActivity {
    Intent homeIntent;
    Button qrbutton;
//    TODO connect to corresponding variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        qrbutton = findViewById(R.id.qrbutton);

        final IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setOrientationLocked(true);
        integrator.setBeepEnabled(false);

        homeIntent = new Intent(Home.this,DocumentFetch.class);


        getRecord();

        qrbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                integrator.initiateScan();
            }
        });

        Button home_button=(Button)findViewById(R.id.home_button);
        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(homeIntent);
                finish();            }
        });
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


        String id1 = "resource%3Aorg.example.basic.Patient%23"+id;


        Call<List<RecordDocument>> call = api.getRecord(id1);


        call.enqueue(new Callback<List<RecordDocument>>() {
            @Override
            public void onResponse(Call<List<RecordDocument>> call, Response<List<RecordDocument>> response) {

                ////Implement the firebase fetching
                Log.d("response",response.toString());
                List<RecordDocument> docs = response.body();
                Toast.makeText(getApplicationContext(), docs.get(0).recordID, Toast.LENGTH_SHORT).show();

//                Intent intent=new Intent(Home.this,DocumentFetch.class);
                homeIntent.putExtra("doc_id",docs.get(0).recordID);
                Toast.makeText(Home.this, "User Record Fetched", Toast.LENGTH_SHORT).show();
//                startActivity(intent);
//                finish();


            }

            @Override
            public void onFailure(Call<List<RecordDocument>> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                //Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
//                Snackbar.make(findViewById(R.id.rootView),"Press Once More To Exit.", Snackbar.LENGTH_SHORT).show();
//                TODO handle condition
            } else {
                //Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                Intent passIntent = new Intent(Home.this,ShareRecord.class);

//                String[] scanParts = result.getContents().split("-");
//                passIntent.putExtra("dealerId",dealerId);
//                passIntent.putExtra("userId",scanParts[0]);
//                passIntent.putExtra("vehicleId",scanParts[1]);
                Toast.makeText(this, result.getContents(), Toast.LENGTH_SHORT).show();
                passIntent.putExtra("doctorId",result.getContents());
                startActivity(passIntent);
                finish();

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
