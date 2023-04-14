package com.mobdeve.s13.kho.denise.schedulecalendar

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class EditEvent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_event)
        val reg = findViewById<Button>(R.id.FinishEditBtn)

        reg.setOnClickListener() {
            val intent = Intent(this, FirestoreEventSchedule::class.java)
            startActivity(intent)
        }
    }
}