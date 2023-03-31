package com.mobdeve.s13.kho.denise.schedulecalendar


import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HostInviteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val en: TextView = itemView.findViewById(R.id.userNameTxt)

    fun bindHostInviteData(hostInvite: HostInvite) {
        en.text = hostInvite.user
    }
}