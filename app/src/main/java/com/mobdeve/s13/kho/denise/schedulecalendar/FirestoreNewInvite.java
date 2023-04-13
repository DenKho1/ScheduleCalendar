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

    public boolean error = true;

    public String eventID = null;

    public boolean getError() {return this.error;}

    public void setError(boolean value) {this.error = value;}

    public String getEventID() {return this.eventID;}

    public void setEventID(String value) {this.eventID = value;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details_host_send_inv);

        Guest = findViewById(R.id.ESendInv);
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


        String guest = Guest.getText().toString();
        String eventID = "VYzRkJEWWhaYAbUkGEXC";
        String eventName = "Basketball";
        String eventDate = "25/08/2023";
        String eventLocation = "Manila";
        String status = "Pending";

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
                               setError(true);
                           } else {
                               Log.d("FSNewInvite", "User Found");
                               setError(false);
                           }
                       }
                   }
               });

        if(!host.equals(guest)){
            if(!getError()) {
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
                                         finish();
                                     } else {
                                         Toast.makeText(FirestoreNewInvite.this, "User already invited", Toast.LENGTH_SHORT).show();
                                     }
                                 }
                             }
                         });
            } else {
                Toast.makeText(FirestoreNewInvite.this, "User does not exist", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(FirestoreNewInvite.this, "Host cannot be guest", Toast.LENGTH_SHORT).show();
        }

    }
}
