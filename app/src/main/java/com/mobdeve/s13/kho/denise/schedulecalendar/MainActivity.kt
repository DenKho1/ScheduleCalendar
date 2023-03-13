package com.mobdeve.s13.kho.denise.schedulecalendar

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val register=findViewById<Button>(R.id.RegisterButton)

        register.setOnClickListener({
            val intent = Intent(this,Register::class.java)
            startActivity(intent)
        })
    }
}