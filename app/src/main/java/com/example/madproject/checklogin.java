package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class checklogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklogin);
    }

    public void asadmin(View view) {
        Intent i = new Intent(checklogin.this,AdminLogin.class);
        startActivity(i);
        finish();
    }
    public void ashallowner(View view) {
        Intent i = new Intent(checklogin.this,HallOwnerLogin.class);
        startActivity(i);
        finish();
    }
    public void asguest(View view) {
        Intent i = new Intent(checklogin.this,GuestLogin.class);
        startActivity(i);
        finish();
    }
}