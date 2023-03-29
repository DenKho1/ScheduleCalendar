package com.mobdeve.s13.kho.denise.schedulecalendar

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class NewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)
        val reg = findViewById<Button>(R.id.FinishBtn)

        reg.setOnClickListener() {
            val intent = Intent(this, EventSchedule::class.java)
            val id1=intent.getStringExtra("id")
            intent.putExtra("id",id1)
            startActivity(intent)
        }
    }
}