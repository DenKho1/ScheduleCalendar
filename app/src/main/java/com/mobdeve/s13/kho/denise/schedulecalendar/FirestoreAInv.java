package com.mobdeve.s13.kho.denise.schedulecalendar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.model.Document;
import com.google.protobuf.Api;

import javax.annotation.Nullable;

public class FirestoreAInv extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private CollectionReference invRef = db.collection("Invite");

    private FirestoreAInvAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        TextView uName = findViewById(R.id.AccUsername);
        TextView uEmail = findViewById(R.id.accEmail);
        TextView uMobile = findViewById(R.id.accMobile);



        SharedPreferences sp = getSharedPreferences("USERNAME",Context.MODE_PRIVATE);
        String named=sp.getString("NAME_KEY","Anon");

//        if(named!=null) {
//            CollectionReference userRef = FirebaseFirestore.getInstance().collection("Users")
//                    .whereEqualTo("username", named)
//                    .get()
//                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                        @Override
//                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                            if(!queryDocumentSnapshots.isEmpty()) {
//                                DocumentSnapshot documentSnapshot = queryDocumentSnapshots.getDocuments().get();
//                            }
//                        }
//                    });
//
//
//        }

        setUpRecyclerView();


    }

    private void setUpRecyclerView() {

        SharedPreferences spGuest = getSharedPreferences("USERNAME", Context.MODE_PRIVATE);
        String user = spGuest.getString("NAME_KEY", "");


        Query query = invRef.whereEqualTo("guest", user)
                            .whereEqualTo("status", "Pending");

        FirestoreRecyclerOptions<FirestoreInvite> options = new
        FirestoreRecyclerOptions.Builder<FirestoreInvite>()
                .setQuery(query, FirestoreInvite.class)
                .build();

        adapter = new FirestoreAInvAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.accRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(this, LinearLayoutManager.VERTICAL , false));
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
