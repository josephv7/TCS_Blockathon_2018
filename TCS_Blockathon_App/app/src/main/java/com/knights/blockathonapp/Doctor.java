package com.knights.blockathonapp;

import com.google.gson.annotations.SerializedName;

class Doctor
{
    @SerializedName("doctorID")
    String doctorID;
    @SerializedName("firstName")
    String firstName;
    @SerializedName("lastName")
    String lastName;
}