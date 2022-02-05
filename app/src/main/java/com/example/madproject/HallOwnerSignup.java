package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madproject.Database.DBHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HallOwnerSignup extends AppCompatActivity {
    EditText name,email,password,phone;
    TextView login;
    DBHelper db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_owner_signup);
        name = findViewById(R.id.Hallownersignupusername);
        email = findViewById(R.id.Hallownersignupemail);
        password = findViewById(R.id.Hallownersignuppassword);
        phone = findViewById(R.id.Hallownersignupphone);
        login = findViewById(R.id.hallownerlogin);
        db = new DBHelper(HallOwnerSignup.this);
        mAuth = FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HallOwnerSignup.this,HallOwnerLogin.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void HallOwnerSignup(View view) {
        String Name = name.getText().toString();
        String Email = email.getText().toString();
        String Pass = password.getText().toString();
        String Phone = phone.getText().toString();
        mAuth.createUserWithEmailAndPassword(Email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Boolean checkinsetdata = db.inserthallownerdata(Name,Email,Pass,Phone);
                    if(checkinsetdata == true){
                        Toast.makeText(HallOwnerSignup.this,"Data Inserted",Toast.LENGTH_SHORT).show();
                        Intent i =new Intent(HallOwnerSignup.this,HallOwnerMainPage.class);
                        startActivity(i);
                        finish();

                    }
                    else
                    {
                        Toast.makeText(HallOwnerSignup.this,"Data not Inserted",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });





    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent i = new Intent(HallOwnerSignup.this,HallOwnerMainPage.class);
            startActivity(i);
            finish();

        }
    }


}