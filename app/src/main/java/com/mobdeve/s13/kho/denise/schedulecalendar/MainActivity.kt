package com.mobdeve.s13.kho.denise.schedulecalendar

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val register=findViewById<Button>(R.id.RegisterButton)
        val login=findViewById<Button>(R.id.LoginButton)
        val name = findViewById<EditText>(R.id.Rusername)
        val password = findViewById<EditText>(R.id.Rpassword)


        register.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)

        }
        login.setOnClickListener() {
            val db= Firebase.firestore
            val usersRef=db.collection(MyFirestoreReferences.USERS_COLLECTION)

            val query=usersRef.whereEqualTo(
               MyFirestoreReferences.USERNAME_FIELD,
                name.toString()
            )



            val intent = Intent(this, EventSchedule::class.java)
            startActivity(intent)
        }
    }
}