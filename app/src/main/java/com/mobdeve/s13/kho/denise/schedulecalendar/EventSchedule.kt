package com.mobdeve.s13.kho.denise.schedulecalendar

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageButton

class EventSchedule : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_event_sched)
            val acc=findViewById<ImageButton>(R.id.AccBtn)
            val new=findViewById<ImageButton>(R.id.AddEvnt)

            acc.setOnClickListener() {
                val intent = Intent(this, Account::class.java)
                startActivity(intent)
            }
            new.setOnClickListener() {
                val intent = Intent(this, NewActivity::class.java)
                startActivity(intent)
            }
        }
    }