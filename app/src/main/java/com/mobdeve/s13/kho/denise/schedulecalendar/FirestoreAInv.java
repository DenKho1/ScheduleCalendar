package com.mobdeve.s13.kho.denise.schedulecalendar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import javax.annotation.Nullable;

public class FirestoreAInv extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private CollectionReference invRef = db.collection("Invite");

    private FirestoreAInvAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        setUpRecyclerView();
    }

    private void setUpRecyclerView() {

        SharedPreferences spGuest = getSharedPreferences("USERNAME", Context.MODE_PRIVATE);
        String user = spGuest.getString("NAME_KEY", "");


        Query query = invRef.whereEqualTo("guest", user);

        FirestoreRecyclerOptions<FirestoreInvite> options = new FirestoreRecyclerOptions.Builder<FirestoreInvite>()
                .setQuery(query, FirestoreInvite.class)
                .build();

        adapter = new FirestoreAInvAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.accRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
