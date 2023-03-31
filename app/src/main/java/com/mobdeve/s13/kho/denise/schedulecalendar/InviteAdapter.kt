package com.mobdeve.s13.kho.denise.schedulecalendar
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class InviteAdapter(private val data: ArrayList<Invite>): RecyclerView.Adapter<InviteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InviteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.accinvite_layout, parent, false)
        return InviteViewHolder(view)
    }

    override fun onBindViewHolder(holder: InviteViewHolder, position: Int) {
        holder.bindInviteData(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}