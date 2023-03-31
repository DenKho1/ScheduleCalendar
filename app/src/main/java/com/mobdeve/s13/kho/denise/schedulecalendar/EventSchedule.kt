package com.mobdeve.s13.kho.denise.schedulecalendar

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
                val id1=intent.getStringExtra("id")
                intent.putExtra("id",id1)
                startActivity(intent)
            }
            new.setOnClickListener() {
                val intent = Intent(this, NewActivity::class.java)
                val id1=intent.getStringExtra("id")
                intent.putExtra("id",id1)
                startActivity(intent)
            }
        }
    }