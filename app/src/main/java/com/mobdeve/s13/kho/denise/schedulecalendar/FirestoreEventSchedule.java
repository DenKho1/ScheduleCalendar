package com.mobdeve.s13.kho.denise.schedulecalendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class FirestoreEventSchedule extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference eventRef = db.collection("Event");

    private FirestoreEventAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_sched);

        setUpRecyclerView();

        ImageButton button = (ImageButton) findViewById(R.id.AddEvnt);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent send = new Intent(FirestoreEventSchedule.this, FirestoreNewEvent.class);
            startActivity(send);
            }
        });
    }

    private void setUpRecyclerView() {
        Query query = eventRef.orderBy("lprio", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<FirestoreEvent> options = new FirestoreRecyclerOptions.Builder<FirestoreEvent>()
                .setQuery(query, FirestoreEvent.class)
                .build();

        adapter = new FirestoreEventAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.schedRecycler);
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
