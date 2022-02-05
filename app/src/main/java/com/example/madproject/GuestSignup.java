package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.madproject.Database.DBHelper;
import com.example.madproject.Database.GuestDBHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class GuestSignup extends AppCompatActivity {
    EditText name,email,password,phone;

    GuestDBHelper db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_signup);
        name = findViewById(R.id.Guestsignupusername);
        email = findViewById(R.id.Guestsignupemail);
        password = findViewById(R.id.Guestsignuppassword);
        phone = findViewById(R.id.Guestsignupphone);

        db = new GuestDBHelper(GuestSignup.this);
        mAuth = FirebaseAuth.getInstance();
    }

    public void GuestSignup(View view) {
        String Name = name.getText().toString();
        String Email = email.getText().toString();
        String Pass = password.getText().toString();
        String Phone = phone.getText().toString();
        mAuth.createUserWithEmailAndPassword(Email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Boolean checkinsetdata = db.insertguestdata(Name,Email,Pass,Phone);
                    Toast.makeText(GuestSignup.this, "Data added in firebase", Toast.LENGTH_SHORT).show();
                    if(checkinsetdata == true){
                        Toast.makeText(GuestSignup.this,"Data Inserted",Toast.LENGTH_SHORT).show();
                        Intent i =new Intent(GuestSignup.this,GuestMainPage.class);
                        startActivity(i);
                        finish();

                    }
                    else
                    {
                        Toast.makeText(GuestSignup.this,"Data not Inserted",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void Guestgotologin(View view) {
    }
}