package com.example.family_bet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.family_bet.Classes.Constants.constants;
import com.example.family_bet.Classes.Enteties.User;
import com.example.family_bet.Classes.Game.Tournament;
import com.example.family_bet.DB.Firestore;
import com.example.family_bet.MESSAGES.FcmNotificationsSender;
import com.example.family_bet.View_Model.Register.Register;
import com.example.family_bet.View_Model.User_Activity.User_Activity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.yodo1.mas.Yodo1Mas;
import com.yodo1.mas.error.Yodo1MasError;
import com.yodo1.mas.helper.model.Yodo1MasAdBuildConfig;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
     Button register,entrance;
     EditText username,password;
    Tournament t;
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initviews();






           ADS.Reward_ad(this);




        Yodo1MasAdBuildConfig config = new Yodo1MasAdBuildConfig.Builder().enableUserPrivacyDialog(true).build();
        Yodo1Mas.getInstance().setAdBuildConfig(config);
        Yodo1Mas.getInstance().initMas(this, "lOwAkhK2fP", new Yodo1Mas.InitListener() {
            @Override
            public void onMasInitSuccessful() {

                Yodo1Mas.getInstance().showInterstitialAd(MainActivity.this);
            }

            @Override
            public void onMasInitFailed(@NonNull Yodo1MasError error) {
            }
        });

       ADS.show_Banner(this);

    }




    //  private Yodo1MasBannerAdView bannerAdView;
    /**
     * init ads
     */
   /* private void inityodo() {
        Yodo1Mas.getInstance().setCOPPA(false);


    }*/

    private void initviews(){
        register=findViewById(R.id.register);
        entrance=findViewById(R.id.entrance);
        username=findViewById(R.id.email);
        password=findViewById(R.id.password);
        register.setOnClickListener(this);
        entrance.setOnClickListener(this);
        register.setBackgroundResource(R.drawable.circle2);
    }
   private boolean iswaiting=true;
    @Override
    public void onClick(View v) {
        if(v==register){

          Intent intent=new Intent(this, Register.class);
          startActivity(intent);

        }
        if(v==entrance){
            try {

               user=username.getText().toString();
               pass=password.getText().toString().trim();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(constants.user);
                databaseReference.child(user).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        try {


                            User user = snapshot.getValue(User.class);
                            if (user.getPassword().trim().equals(pass)) {
                                SharedPreferences sharedPreferences=getSharedPreferences(constants.user,0);
                                SharedPreferences.Editor editor=sharedPreferences.edit();
                                editor.putString("username",user.getUsername());
                                editor.putString("name",user.getName());
                                editor.commit();
                            Intent intent=new Intent(MainActivity.this, User_Activity.class);
                            startActivity(intent);
                            } else {
                                Toast.makeText(MainActivity.this, "wrong", Toast.LENGTH_SHORT).show();

                            }
                        }
                        catch (Exception e){
                            Toast.makeText(MainActivity.this, "invalid username", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
            catch (Exception e){

            }


        }
    }
    String user="",pass="";

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {


        }
    }
    private class MyAsyncTask extends AsyncTask<ProgressDialog, ProgressDialog, String> {

        @Override
        protected String doInBackground(ProgressDialog... voids) {
            try {

                ArrayList<String> arrayList=new ArrayList<>();
                arrayList.add("c_Basketball_yair_winner1");
                CollectionReference ref = FirebaseFirestore.getInstance().collection(constants.tournament);
                ref.whereIn("Key",arrayList).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshot) {
                      //  Toast.makeText(User_Activity.this, String.valueOf(documentSnapshot.isEmpty()), Toast.LENGTH_SHORT).show();
                        try {
                            for (DocumentSnapshot doc : documentSnapshot.getDocuments()) {


                                //    Toast.makeText(User_Activity.this, "in", Toast.LENGTH_SHORT).show();

                                Tournament t = Firestore.getTour(doc);
                                Gson gson=new Gson();


                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            int i=0;
                                            String send=gson.toJson(t);
                                            FcmNotificationsSender sender = new FcmNotificationsSender();
                                            sender.sendNotification("/topics/winner","new 1","restart");
                                         //   Toast.makeText(MainActivity.this, String.valueOf(send.length()), Toast.LENGTH_SHORT).show();
                                            while(i<send.length()) {
                                                String current="";
                                                if(send.length()-i<1000){
                                                    current=send.substring(i,send.length());
                                                    i=send.length();
                                                }
                                                else {
                                                    current=send.substring(i,i+1000);
                                                    i=i+1000;
                                                }


                                                sender.sendNotification("/topics/winner", "new 1",current);
                                                double in=Double.valueOf(i/send.length());
                                                voids[0].setMessage(String.valueOf(in)+" percent");
                                            }
                                            voids[0].dismiss();
                                          //  sender.sendNotification("/topics/winner", "send",gson.toJson(t));
                                        } catch (IOException | JSONException e) {
                                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }).start();

                            }




                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
            catch (Exception e){
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            // handle the response here
        }
    }

// to execute the AsyncTask







}