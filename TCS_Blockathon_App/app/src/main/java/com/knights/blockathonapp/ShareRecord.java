package com.knights.blockathonapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ShareRecord extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_record);

        Intent doctorGetIntent = getIntent();
        String doctorId = doctorGetIntent.getStringExtra("doctorId");

    }
}
