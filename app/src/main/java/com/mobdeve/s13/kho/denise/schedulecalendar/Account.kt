package com.mobdeve.s13.kho.denise.schedulecalendar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Account : AppCompatActivity() {
    private val inviteList: ArrayList<Invite> = InviteGenerator.genData()
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        val name = findViewById<TextView>(R.id.AccUsername)
        val email = findViewById<TextView>(R.id.accEmail)
        val mobile = findViewById<TextView>(R.id.accMobile)
        val icon=findViewById<ImageView>(R.id.icon)
        name.text = intent.getStringExtra("Username")
        email.text= intent.getStringExtra("Email")
        mobile.text= intent.getStringExtra("Mobile")
        icon.setImageResource(R.drawable.icon)

        this.recyclerView = findViewById(R.id.accRecycler)
        this.recyclerView.adapter = InviteAdapter(this.inviteList)
        this.recyclerView.layoutManager = LinearLayoutManager(this)


        val register=findViewById<Button>(R.id.AccLogout)

        register.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val sched=findViewById<ImageButton>(R.id.accsched)

        sched.setOnClickListener {
            val intent2 = Intent(this, EventSchedule::class.java)
            startActivity(intent2)
        }

    }
}