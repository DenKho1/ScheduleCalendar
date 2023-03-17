package com.mobdeve.s13.kho.denise.schedulecalendar

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup

class HostInviteAdapter(private val data: ArrayList<HostInvite>): RecyclerView.Adapter<HostInviteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HostInviteViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(
            R.layout.schedhost_layout,
            parent,
            false)
        return HostInviteViewHolder(view)
    }

    override fun onBindViewHolder(holder: HostInviteViewHolder, position: Int) {
        holder.bindHostInviteData(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}