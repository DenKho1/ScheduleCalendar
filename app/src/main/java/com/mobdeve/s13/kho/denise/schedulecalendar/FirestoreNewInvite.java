package com.mobdeve.s13.kho.denise.schedulecalendar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class FirestoreNewInvite extends AppCompatActivity {

    private EditText Guest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details_host_send_inv);

        Guest = findViewById(R.id.ESendInv);

//      TODO Make it so that eventID is with the other variables in saveInvite, use shared prefs as well
//      TODO delete stuff from here
        String eventName = "Basketball";
        String eventDate = "25/08/2023";
        String eventLocation = "Manila";

        CollectionReference eventRef = FirebaseFirestore.getInstance().collection("Event");
        eventRef.whereEqualTo("lnameTxt", eventName)
                .whereEqualTo("ldateTxt", eventDate)
                .whereEqualTo("llocationTxt", eventLocation)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            QuerySnapshot querySnapshot = task.getResult();
                            if(!querySnapshot.isEmpty()) {
                                DocumentSnapshot documentSnapshot = querySnapshot.getDocuments().get(0);
                                SharedPreferences spEvent = getSharedPreferences("EVENT_ID", Context.MODE_PRIVATE);
                                spEvent.edit().putString("EVENT_ID_KEY", documentSnapshot.getId()).apply();
                            }
                        }
                    }
                });
//      TODO to here
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.new_invite_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.saveInvite:
                saveInvite();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveInvite() {



        SharedPreferences spHost = this.getSharedPreferences("USERNAME", Context.MODE_PRIVATE);
        String host = spHost.getString("NAME_KEY", "");

        SharedPreferences spEvent = getSharedPreferences("EVENT_ID", Context.MODE_PRIVATE);
        String eventID = spEvent.getString("EVENT_ID_KEY", "null");

        String guest = Guest.getText().toString();
        String status = "Pending";

//      TODO modify this so that it uses shared prefs instead of hard coded
        SharedPreferences spE=getSharedPreferences("EVENT_DETAILS", Context.MODE_PRIVATE);
        String eventName = spE.getString("EVENT_NAME_KEY","EVENT");
        String eventDate = spE.getString("EVENT_DATE_KEY","0/0/0000");
        String eventLocation = spE.getString("EVENT_LOCATION_KEY","EARTH");

        CollectionReference userRef = FirebaseFirestore.getInstance().collection("Users");
        userRef.whereEqualTo("username", guest)
               .get()
               .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                   @Override
                   public void onComplete(@NonNull Task<QuerySnapshot> task) {
                       if(task.isSuccessful()) {
                           QuerySnapshot querySnapshot = task.getResult();
                           if(querySnapshot.isEmpty()){
                               Log.d("FSNewInvite", "User not Found");
                               SharedPreferences spUser = getSharedPreferences("USER_NAME", Context.MODE_PRIVATE);
                               spUser.edit().putBoolean("USER_NAME_KEY", false).apply();
                           } else {
                               Log.d("FSNewInvite", "User Found");
                               SharedPreferences spUser = getSharedPreferences("EVENT_ID", Context.MODE_PRIVATE);
                               spUser.edit().putBoolean("USER_NAME_KEY", true).apply();
                           }
                       }
                   }
               });

        SharedPreferences spUser = getSharedPreferences("USER_NAME", Context.MODE_PRIVATE);
        boolean found = spUser.getBoolean("USER_NAME_KEY", true);

        if(!host.equals(guest)){
            if(found) {
                CollectionReference inviteRef = FirebaseFirestore.getInstance().collection("Invite");
                inviteRef.whereEqualTo("guest", guest)
                         .whereEqualTo("eventID", eventID)
                         .whereEqualTo("host", host)
                         .get()
                         .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                             @Override
                             public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                 if(task.isSuccessful()) {
                                     QuerySnapshot querySnapshot = task.getResult();
                                     if(querySnapshot.isEmpty()){
                                         inviteRef.add(new FirestoreInvite(guest, host, eventID, eventName, eventDate, eventLocation, status));
                                         Toast.makeText(FirestoreNewInvite.this, "User successfully invited", Toast.LENGTH_SHORT).show();
                                         SharedPreferences spUser = getSharedPreferences("USER_NAME", Context.MODE_PRIVATE);
                                         spUser.edit().remove("USER_NAME_KEY").commit();
                                         SharedPreferences spEvent = getSharedPreferences("EVENT_ID", Context.MODE_PRIVATE);
                                         spEvent.edit().remove("EVENT_ID_KEY").commit();
                                         finish();
                                     } else {
                                         Toast.makeText(FirestoreNewInvite.this, "User already invited", Toast.LENGTH_SHORT).show();
                                         SharedPreferences spUser = getSharedPreferences("USER_NAME", Context.MODE_PRIVATE);
                                         spUser.edit().remove("USER_NAME_KEY").commit();
                                     }
                                 }
                             }
                         });
            } else {
                Toast.makeText(FirestoreNewInvite.this, "User does not exist", Toast.LENGTH_SHORT).show();
                spUser.edit().remove("USER_NAME_KEY").commit();
            }
        } else {
            Toast.makeText(FirestoreNewInvite.this, "Host cannot be guest", Toast.LENGTH_SHORT).show();
            spUser.edit().remove("USER_NAME_KEY").commit();
        }

    }
}
