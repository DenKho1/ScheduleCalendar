package com.mobdeve.s13.kho.denise.schedulecalendar

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

class Account : AppCompatActivity() {
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

        val register=findViewById<Button>(R.id.AccLogout)

        register.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val sched=findViewById<ImageButton>(R.id.acctosched)

        sched.setOnClickListener {
            val intent = Intent(this,EventSched::class.java)
            startActivity(intent)
        }


    }
}