package com.mobdeve.s13.kho.denise.schedulecalendar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import org.w3c.dom.Text;

public class FirestoreEventAdapter extends FirestoreRecyclerAdapter<FirestoreEvent, FirestoreEventAdapter.FirestoreEventHolder> {

    public FirestoreEventAdapter(@NonNull FirestoreRecyclerOptions<FirestoreEvent> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull FirestoreEventHolder holder, int position, @NonNull FirestoreEvent model) {
        holder.LNameTxt.setText(model.getLNameTxt());
        holder.LDateTxt.setText(model.getLDateTxt());
        holder.LLocationTxt.setText(model.getLLocationTxt());
        holder.Lprio.setText(String.valueOf(model.getLPrio()));
    }

    @NonNull
    @Override
    public FirestoreEventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_event_sched_layout,
                parent, false);
        return new FirestoreEventHolder(v);
    }

    class FirestoreEventHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            TextView LNameTxt;
            TextView LDateTxt;
            TextView LLocationTxt;

            TextView Lprio;


        public FirestoreEventHolder(@NonNull View itemView) {
            super(itemView);
            LNameTxt = itemView.findViewById(R.id.LNameTxt);
            LDateTxt = itemView.findViewById(R.id.LDateTxt);
            LLocationTxt = itemView.findViewById(R.id.LLocationTxt);
            Lprio = itemView.findViewById(R.id.LPrio);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent i=new Intent(view.getContext(), EditEvent.class);
            view.getContext().startActivity(i);
        }
    }
}
