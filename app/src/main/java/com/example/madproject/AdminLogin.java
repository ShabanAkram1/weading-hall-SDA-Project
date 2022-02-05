package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {
    EditText name,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        name= findViewById(R.id.Adminusername);
        pass = findViewById(R.id.Adminpassword);

    }

    public void AdminLogin(View view) {
        String Name = name.getText().toString();
        String Pass = pass.getText().toString();
        if(Name.equals("Admin") && Pass.equals("Admin")){
            Intent i = new Intent(AdminLogin.this,Adminmainpage.class);
            startActivity(i);
            finish();
              }
    }
}