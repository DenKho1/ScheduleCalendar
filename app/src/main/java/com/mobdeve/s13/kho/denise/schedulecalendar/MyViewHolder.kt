package com.mobdeve.s13.kho.denise.schedulecalendar

import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.widget.TextView

class MyViewHolder (itemView: View): ViewHolder(itemView) {
    private val en: TextView = itemView.findViewById(R.id.LNameTxt)
    private val el: TextView = itemView.findViewById(R.id.LLocationTxt)
    private val ed: TextView = itemView.findViewById(R.id.LDateTxt)

    fun bindData(event: Event) {
        en.text = event.eventName
        el.text = event.eventLoc
        ed.text = event.eventDate
    }
}