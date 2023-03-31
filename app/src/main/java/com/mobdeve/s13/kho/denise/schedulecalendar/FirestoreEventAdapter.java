package com.mobdeve.s13.kho.denise.schedulecalendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class FirestoreEventAdapter extends FirestoreRecyclerAdapter<FirestoreEvent, FirestoreEventAdapter.FirestoreEventHolder> {

    public FirestoreEventAdapter(@NonNull FirestoreRecyclerOptions<FirestoreEvent> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull FirestoreEventHolder holder, int position, @NonNull FirestoreEvent model) {
        holder.LNameTxt.setText(model.getLNameTxt());
        holder.LDateTxt.setText(model.getLDateTxt());
        holder.LLocationTxt.setText(model.getLLocationTxt());
    }

    @NonNull
    @Override
    public FirestoreEventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_event_sched_layout,
                parent, false);
        return new FirestoreEventHolder(v);
    }

    class FirestoreEventHolder extends RecyclerView.ViewHolder {
            TextView LNameTxt;
            TextView LDateTxt;
            TextView LLocationTxt;



        public FirestoreEventHolder(@NonNull View itemView) {
            super(itemView);
            LNameTxt = itemView.findViewById(R.id.LNameTxt);
            LDateTxt = itemView.findViewById(R.id.LDateTxt);
            LLocationTxt = itemView.findViewById(R.id.LLocationTxt);
        }
    }
}
