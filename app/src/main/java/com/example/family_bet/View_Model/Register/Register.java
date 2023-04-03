package com.example.family_bet.View_Model.Register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.family_bet.Classes.Constants.constants;
import com.example.family_bet.Classes.Enteties.User;
import com.example.family_bet.MainActivity;
import com.example.family_bet.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity implements View.OnClickListener {
   TextView register;
   boolean exist=true;
    TextInputEditText name,username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init_views();
    }
    /*
    init views
     */
    private void init_views(){
        register=findViewById(R.id.register);
        name=findViewById(R.id.name);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==register){
            if(username.getText().length()>0&&name.getText().length()>0&&password.getText().length()>0) {
                find_user();
            }
            else {
                Toast.makeText(this, "empty field", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void find_user() {
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference(constants.user).child(username.getText().toString());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user=snapshot.getValue(User.class);
                if(user==null){
                    exist=false;
                    user=new User(username.getText().toString(),password.getText().toString(),name.getText().toString());
                    Intent intent=new Intent(Register.this, MainActivity.class);
                    startActivity(intent);
                    user=null;
                }
                else if(exist){
                    Toast.makeText(Register.this, "user is already exist", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}