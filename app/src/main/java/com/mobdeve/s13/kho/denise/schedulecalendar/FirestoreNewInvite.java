package com.mobdeve.s13.kho.denise.schedulecalendar;

import android.annotation.SuppressLint;
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
import com.google.firebase.firestore.QuerySnapshot;

public class FirestoreNewInvite extends AppCompatActivity {

    private EditText Guest;

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
        String guest = Guest.getText().toString();
        String host = "d";
        String eventID = "VYzRkJEWWhaYAbUkGEXC";
        String eventName = "Basketball";
        String eventDate = "25/08/2023";
        String eventLocation = "Manila";
        String status = "Pending";

        if(!host.equals(guest)){
            CollectionReference inviteRef = FirebaseFirestore.getInstance().collection("Invite");
            inviteRef.whereEqualTo("guest", guest)
                    .whereEqualTo("eventID", eventID)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()) {
                                for(DocumentSnapshot document : task.getResult()) {
                                    if(document.exists()) {
                                        Toast.makeText(FirestoreNewInvite.this, "User already invited", Toast.LENGTH_SHORT).show();
                                    } else {
                                        inviteRef.add(new FirestoreInvite(guest, host, eventID, eventName, eventDate, eventLocation, status));
                                        Toast.makeText(FirestoreNewInvite.this, "User successfully invited", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                }
                            }
                        }
                    });
        } else {
            Toast.makeText(FirestoreNewInvite.this, "Host cannot be guest", Toast.LENGTH_SHORT).show();
        }





    }
}
