package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.madproject.Database.DBHelper;
import com.example.madproject.model.HallOwnerLogininfo;
import com.example.madproject.model.Hallinfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class addhallinfo extends AppCompatActivity {
    EditText hallname,hallcity,halllocation,hallcapacity;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    String name;

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addhallinfo);
        hallname = findViewById(R.id.Hallownerhallname);
        hallcity = findViewById(R.id.Hallownerhallcity);
        halllocation = findViewById(R.id.Hallownerhalllocation);
        hallcapacity = findViewById(R.id.Hallownerhallcapacity);
        mAuth = FirebaseAuth.getInstance();
        db = new DBHelper(addhallinfo.this);
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String email = user.getEmail();
            List<HallOwnerLogininfo> all = db.getsingledata(email);
            for(int i = 0 ; i < all.size() ; i++){
                name = all.get(i).getName();
            }
        }

    }
    public void Addhalldetails(View view) {
        String HALLNAME,HALLLOCATION,HALLCAPACITY,HALLCITY;
        HALLCITY =hallcity.getText().toString();
        HALLNAME = hallname.getText().toString();
        HALLLOCATION = halllocation.getText().toString();
        HALLCAPACITY = hallcapacity.getText().toString();
        Hallinfo hall = new Hallinfo(HALLNAME,HALLLOCATION,HALLCAPACITY,HALLCITY,name);
                boolean checkinsert = db.inserthalldata(hall);
        if(checkinsert == true){
            Toast.makeText(addhallinfo.this, "Data inserted", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(addhallinfo.this, "Data not inserted", Toast.LENGTH_SHORT).show();
        }
    }

    public void HallownerSignout(View view) {
        mAuth.signOut();
        Intent i = new Intent(addhallinfo.this,HallOwnerLogin.class);
        startActivity(i);
        finish();

    }

    public void Seeprofile(View view) {
        Intent i = new Intent(addhallinfo.this,HallOwnerMainPage.class);
        startActivity(i);
        finish();
    }



    public void Updatehalldetails(View view) {

    }
}