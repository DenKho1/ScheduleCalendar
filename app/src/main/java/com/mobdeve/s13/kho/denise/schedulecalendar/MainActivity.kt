package com.mobdeve.s13.kho.denise.schedulecalendar

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
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

        login.setOnClickListener{
            val db= Firebase.firestore
            val usersRef=db.collection(MyFirestoreReferences.USERS_COLLECTION)

            usersRef.whereEqualTo(
               MyFirestoreReferences.USERNAME_FIELD,
                name.text.toString()
            )

            var tempname=""
            var tpassword=""
            val usersRef2 = db.collection(MyFirestoreReferences.USERS_COLLECTION).document(usersRef.document().id)
            usersRef2.get()
                .addOnSuccessListener { documentSnapshot ->
                    val user = documentSnapshot.toObject<Users>()
                        if (user != null) {
                            tempname = user.username
                            tpassword=user.password
                        }


                }

            if(tempname.equals(name.text.toString()) && tpassword.equals(password.text.toString()) )
            {
                val intent = Intent(this, Account::class.java)
                intent.putExtra("id",usersRef.document().id)
                startActivity(intent)

            }

            else
            {
                val toast=
                    Toast.makeText(applicationContext,"User does not exist", Toast.LENGTH_LONG)
                toast.show()
            }




        }
    }
}