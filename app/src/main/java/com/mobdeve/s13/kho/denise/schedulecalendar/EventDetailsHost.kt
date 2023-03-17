package com.mobdeve.s13.kho.denise.schedulecalendar

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button

class EventDetailsHost : AppCompatActivity() {
    private val hostInviteList: ArrayList<HostInvite> = HostInviteGenerator.genData()
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details_host)
        val edit = findViewById<Button>(R.id.EditEBtn)
        val send = findViewById<Button>(R.id.SendInvitesBtn)

        this.recyclerView = findViewById(R.id.invRecyclerView)
        this.recyclerView.adapter = HostInviteAdapter(this.hostInviteList)
        this.recyclerView.layoutManager = LinearLayoutManager(this)

        edit.setOnClickListener() {
            val intent = Intent(this, EditEvent::class.java)
            startActivity(intent)
        }

        send.setOnClickListener() {
            val intent = Intent(this, EventDetailsHostSendInv::class.java)
            startActivity(intent)
        }
    }
}