package com.mobdeve.s13.kho.denise.schedulecalendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import javax.annotation.Nullable;

public class FirestoreAInvAdapter extends FirestoreRecyclerAdapter<FirestoreInvite, FirestoreAInvAdapter.FirestoreAInviteHolder> {

    public FirestoreAInvAdapter(@NonNull FirestoreRecyclerOptions<FirestoreInvite> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull FirestoreAInviteHolder holder, int position, @NonNull FirestoreInvite model) {
        holder.inviUser.setText(model.getHost());
        holder.inviTitle.setText(model.getEventName());
        holder.inviTitle2.setText(model.getEventDate());
    }

    @NonNull
    @Override
    public FirestoreAInviteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.accinvite_layout,
                parent, false);
        return new FirestoreAInviteHolder(v);
    }

    class FirestoreAInviteHolder extends RecyclerView.ViewHolder {
        TextView inviUser;

        TextView inviTitle;

        TextView inviTitle2;

        public FirestoreAInviteHolder(@NonNull View itemView) {
            super(itemView);
            inviUser = itemView.findViewById(R.id.inviUser);
            inviTitle = itemView.findViewById(R.id.inviTitle);
            inviTitle2 = itemView.findViewById(R.id.inviTitle2);
        }
    }
}
