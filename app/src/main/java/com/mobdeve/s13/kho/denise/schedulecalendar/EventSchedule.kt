package com.mobdeve.s13.kho.denise.schedulecalendar

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.ImageButton

class EventSchedule : AppCompatActivity() {
    private val eventList: ArrayList<Event> = DataGenerator.generateData()
    private lateinit var recyclerView: RecyclerView
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_event_sched)
            val acc=findViewById<ImageButton>(R.id.AccBtn)
            val new=findViewById<ImageButton>(R.id.AddEvnt)

            this.recyclerView = findViewById(R.id.recyclerView)
            this.recyclerView.adapter = MyAdapter(this.eventList)
            this.recyclerView.layoutManager = LinearLayoutManager(this)

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