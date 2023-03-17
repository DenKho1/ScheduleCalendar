package com.mobdeve.s13.kho.denise.schedulecalendar

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class EventDetailsHostSendInv : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details_host_send_inv)
        val reg = findViewById<Button>(R.id.ESendInvBtn)

        reg.setOnClickListener() {
            val intent = Intent(this, EventDetailsHost::class.java)
            startActivity(intent)
        }
    }
}