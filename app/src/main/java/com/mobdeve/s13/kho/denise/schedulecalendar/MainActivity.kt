package com.mobdeve.s13.kho.denise.schedulecalendar

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val register=findViewById<Button>(R.id.RegisterButton)
        val login=findViewById<Button>(R.id.LoginButton)

        register.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
        login.setOnClickListener() {
            val intent = Intent(this, EventSchedule::class.java)
            startActivity(intent)
        }
    }
}