package com.example.family_bet.View_Model.User_Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.family_bet.ADS;
import com.example.family_bet.Classes.Constants.constants;
import com.example.family_bet.Classes.Enteties.User;
import com.example.family_bet.Classes.Game.Tournament;
import com.example.family_bet.Classes.Game.team.Team;
import com.example.family_bet.DB.Firestore;
import com.example.family_bet.R;
import com.example.family_bet.View_Model.Tournament_activities.Add.Add_Activity;
import com.example.family_bet.View_Model.Tournament_activities.join.Choosing_tours_details;
import com.example.family_bet.View_Model.User_Activity.Delar_activity.Dealear_Menu;
import com.example.family_bet.View_Model.User_Activity.Predictor_Activity.Predictor_Activity;
import com.example.family_bet.databinding.ActivityUserBinding;
import com.example.family_bet.databinding.AddTeamBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.yodo1.mas.Yodo1Mas;
import com.yodo1.mas.error.Yodo1MasError;
import com.yodo1.mas.helper.model.Yodo1MasAdBuildConfig;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class User_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView;
    private DrawerLayout drawer;
    User user=new User();
    TextView textView;
    boolean isProfile=true;
    ActivityUserBinding user_binding;
    CircleImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user_binding=ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(user_binding.getRoot());
        drawer = findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.menu);
        navigationView.setItemIconTintList(null);

        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
      imageView = headerView.findViewById(R.id.image);
        textView=headerView.findViewById(R.id.username);
        init_user();



imageView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

            Intent gallery=new Intent();
            gallery.setType("image/*");
            isProfile=true;
            gallery.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(gallery,"Select picture"),PICK_IM);

    }
});

        Yodo1MasAdBuildConfig config = new Yodo1MasAdBuildConfig.Builder().enableUserPrivacyDialog(true).build();
        Yodo1Mas.getInstance().setAdBuildConfig(config);
        Yodo1Mas.getInstance().initMas(this, "lOwAkhK2fP", new Yodo1Mas.InitListener() {
            @Override
            public void onMasInitSuccessful() {

                Yodo1Mas.getInstance().showInterstitialAd(User_Activity.this);
            }

            @Override
            public void onMasInitFailed(@NonNull Yodo1MasError error) {
            }
        });
        ADS.show_Banner(this);
    }
ArrayList<Tournament> tournaments;
    private void init_tournaments() {
        tournaments=new ArrayList<>();
     try {


         CollectionReference ref = FirebaseFirestore.getInstance().collection(constants.tournament);

         for(String s:user.getTournaments_id()){
             try {


                 int count = 0;
                 for (String string : user.getTournaments_id()) {
                     if (string.equals(s)) {
                         count++;

                     }

                 }
                 if (count > 1) {
                     user.getTournaments_id().remove(s);
                 }
             }catch (Exception e){

             }

         }
         ref.whereIn(constants.key, user.getTournaments_id()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
             @Override
             public void onSuccess(QuerySnapshot documentSnapshot) {
                 Toast.makeText(User_Activity.this, String.valueOf(documentSnapshot.isEmpty()), Toast.LENGTH_SHORT).show();
                 try {
                     for (DocumentSnapshot doc : documentSnapshot.getDocuments()) {


                         //    Toast.makeText(User_Activity.this, "in", Toast.LENGTH_SHORT).show();

                             Tournament t = Firestore.getTour(doc);
                             tournaments.add(t);

                     }


                     InitAdapter();

                 } catch (Exception e) {
                       user_binding.username.setText(e.getMessage());
                 }
             }
         });
     }
     catch (Exception e){
         user_binding.username.setText(e.getMessage());
     }

    }

    /**
     * init Adapter and listeners
     */
    public void InitAdapter(){
        Tournament_Adapter tournament_adapter = new Tournament_Adapter(User_Activity.this, tournaments);
        user_binding.list.setAdapter(tournament_adapter);
        user_binding.list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tournament tournament = tournaments.get(position);
                if (tournament.getDealer().equals(user.getUsername())) {
                    Intent intent = new Intent(User_Activity.this, Dealear_Menu.class);
                    Gson gson=new Gson();

                    intent.putExtra(constants.tournament, gson.toJson(tournament));
                    intent.putExtra(constants.user, gson.toJson(user));
                    startActivity(intent);


                } else {

                    Intent intent = new Intent(User_Activity.this, Predictor_Activity.class);
                    Gson gson=new Gson();
                    String json=gson.toJson(tournament);
                    intent.putExtra(constants.tournament, json);
                    startActivity(intent);
                }
            }
        });
    }
    /**
     * init user from REALTIME DATABASE
     */
    private void init_user() {
        String username=constants.get_Curret_User(this);
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference(constants.user);
        databaseReference.child(username).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user=snapshot.getValue(User.class);
              //  Toast.makeText(User_Activity.this, user.getTournaments_id().toString(), Toast.LENGTH_SHORT).show();
                textView.setText(user.getUsername());
                try {
                  if(user.getProfile()!=null){
                      Picasso.get().load(user.getProfile()).into(imageView);
                  }
                }
                catch (Exception e){

                }
                init_tournaments();
                Toast.makeText(User_Activity.this, user.getUsername(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getTitle().toString().equals(constants.Join_tour)){


                    Intent intent = new Intent(this, Choosing_tours_details.class);
                    intent.putExtra("type", constants.country);
                    startActivity(intent);


        }
        else if(item.getTitle().toString().equals(constants.add_tour)){
         if(user.getCount_tours()>5){
             Toast.makeText(this, "You can't open more than 5 tournaments. please delete one of the exist tours", Toast.LENGTH_LONG).show();
         }
         else {
             Intent intent = new Intent(this, Add_Activity.class);
             Gson gson=new Gson();
             intent.putExtra(constants.user,gson.toJson(user));
             startActivity(intent);
         }
        }
        else if(item.getTitle().toString().equals(constants.add_team)){
             add_team_dialog();
        }
        return false;
    }
    AddTeamBinding binding;
    Dialog d;
    public static final int PICK_IM=1;
    //add team dialog
    private void add_team_dialog() {
        d = new Dialog(this);
         binding= AddTeamBinding.inflate(getLayoutInflater());
        d.setContentView(binding.getRoot());
        //upload picture
        binding.upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  if(v==binding.upload){
                      /*
                      if(v==binding.upload){
                          Intent gallery=new Intent();
                          gallery.setType("image/*");
                          gallery.setAction(Intent.ACTION_GET_CONTENT);
                          startActivityForResult(Intent.createChooser(gallery,"Select picture"),PICK_IM);
                      }*/
                      Picasso.get().load(Uri.parse(binding.url.getText().toString())).into(binding.image);
                  }
            }
        });
        //save picture
        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v==binding.save){
                    if(binding.name.getText().length()>0&&binding.url.getText().length()>0){
                       // processupload(uri);
                        team=new Team();
                        team.setName(binding.name.getText().toString());
                        team.setPicture(binding.url.getText().toString());
                        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference(constants.team);
                        databaseReference.child(binding.name.getText().toString()).setValue(team);
                        d.dismiss();
                    }
                    else{
                        Toast.makeText(User_Activity.this, "add name", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        d.show();
    }
    Uri uri;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IM&&resultCode==RESULT_OK){
            if(isProfile){
                try {


                    uri = data.getData();
                    View headerView = navigationView.getHeaderView(0);
                    CircleImageView imageView = headerView.findViewById(R.id.image);
                    imageView.setImageBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), uri));
                    imageView.setBackground(null);
                    Upload_Profile_Image(uri);

                }
                catch (Exception e){

                }
            }
            else {
                try {
                    uri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    binding.image.setImageBitmap(bitmap);


                } catch (Exception e) {

                }
            }
        }
    }
    private Team team;
    /*
   upload picture to database
    */
    public void Upload_Profile_Image(Uri filepath)
    {
        final ProgressDialog pd=new ProgressDialog(this);
        pd.setTitle("File Uploading....!!!");
        pd.show();
        StorageReference storageReference= FirebaseStorage.getInstance().getReference();
        final StorageReference reference=storageReference.child("profiles/"+user.getUsername()+".png");
        reference.putFile(filepath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                user.setProfile(uri.toString());
                                User.Save_user(user);
                                pd.dismiss();
                            }


                        });

                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        float percent=(100*taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                        pd.setMessage("Uploaded :"+(int)percent+"%");
                    }
                });
    }





}