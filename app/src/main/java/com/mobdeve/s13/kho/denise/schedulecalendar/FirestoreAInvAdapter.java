package com.mobdeve.s13.kho.denise.schedulecalendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class FirestoreAInvAdapter extends FirestoreRecyclerAdapter<FirestoreInvite, FirestoreAInvAdapter.FirestoreAInviteHolder> {

    public FirestoreAInvAdapter(@NonNull FirestoreRecyclerOptions<FirestoreInvite> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull FirestoreAInviteHolder holder, int position, @NonNull FirestoreInvite model) {
        holder.host.setText(model.getHost());
        holder.eventName.setText(model.getEventName());
        holder.eventDate.setText(model.getEventDate());
        holder.guest.setText(model.getGuest());
        holder.eventID.setText(model.getEventID());
        holder.eventLocation.setText(model.getEventLocation());
        holder.status.setText(model.getStatus());
    }

    @NonNull
    @Override
    public FirestoreAInviteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.accinvite_layout,
                parent, false);
        return new FirestoreAInviteHolder(v);
    }

    class FirestoreAInviteHolder extends RecyclerView.ViewHolder {
        TextView host;
        TextView eventName;
        TextView eventDate;
        TextView guest, eventID, eventLocation, status;


        public FirestoreAInviteHolder(@NonNull View itemView) {
            super(itemView);
            host = itemView.findViewById(R.id.host);
            eventName = itemView.findViewById(R.id.eventName);
            eventDate = itemView.findViewById(R.id.eventDate);
            guest = itemView.findViewById(R.id.guestTxt);
            eventID = itemView.findViewById(R.id.eventIDTxt);
            eventLocation = itemView.findViewById(R.id.eventLocationTxt);
            status = itemView.findViewById(R.id.statusTxt);
        }
    }
}
