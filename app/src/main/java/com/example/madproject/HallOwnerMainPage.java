package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madproject.Database.DBHelper;
import com.example.madproject.model.HallOwnerLogininfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class HallOwnerMainPage extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser User;
    TextView name;
    TextView Email;
    TextView Pass;
    TextView Phone;
    DBHelper db;
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_owner_main_page);
        mAuth = FirebaseAuth.getInstance();
        name = findViewById(R.id.Hallownerusername);
        Email = findViewById(R.id.Hallowneremail);
        Pass = findViewById(R.id.Hallownerpassword);
        Phone = findViewById(R.id.Hallownerphone);
        User =FirebaseAuth.getInstance().getCurrentUser();
        db = new DBHelper(HallOwnerMainPage.this);
        if (User != null) {
            String email = User.getEmail();
            List<HallOwnerLogininfo> all = db.getsingledata(email);
            for(int i = 0 ; i < all.size() ; i++){
                name.setText(all.get(i).getName());
                Email.setText(all.get(i).getEmail());
                Pass.setText(all.get(i).getPass());
                Phone.setText(all.get(i).getPhone());
            }
             }




    }



    public void addhalldetails(View view) {
        Intent i = new Intent(HallOwnerMainPage.this,addhallinfo.class);
        startActivity(i);
    }

    public void HallownerSignout(View view) {
        mAuth.signOut();
        Intent i = new Intent(HallOwnerMainPage.this,HallOwnerLogin.class);
        startActivity(i);
        finish();
    }

    public void updateprofile(View view) {
        String n = name.getText().toString();
        String e = Email.getText().toString();
        String pas = Pass.getText().toString();
        String p = Phone.getText().toString();
        boolean checkupdate = db.updatehallownerdata(n,e,pas,p);
        if(checkupdate == true){
            Toast.makeText(HallOwnerMainPage.this, "Updated successfully", Toast.LENGTH_SHORT).show();
        }
    }
}