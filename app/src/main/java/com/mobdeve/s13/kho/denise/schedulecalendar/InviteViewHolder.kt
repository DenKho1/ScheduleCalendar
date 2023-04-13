package com.mobdeve.s13.kho.denise.schedulecalendar

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class InviteViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
    private val en: TextView = itemView.findViewById(R.id.host)
    private val el: TextView = itemView.findViewById(R.id.eventName)
    private val ed: TextView = itemView.findViewById(R.id.eventDate)

    fun bindInviteData(invite: Invite) {
        en.text = invite.user
        el.text = invite.title
        ed.text = invite.date
    }
}