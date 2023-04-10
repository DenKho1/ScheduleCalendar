package com.mobdeve.s13.kho.denise.schedulecalendar

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "LOGINActivity"
    }
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val register = findViewById<Button>(R.id.RegisterButton)
        val login = findViewById<Button>(R.id.LoginButton)
        val name = findViewById<EditText>(R.id.Rusername)
        val password = findViewById<EditText>(R.id.Rpassword)
        var e = 0
        var id = ""
        register.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)

        }

        login.setOnClickListener {
            val db = Firebase.firestore
            val usersRef = db.collection(MyFirestoreReferences.USERS_COLLECTION)
            usersRef.whereEqualTo(
                MyFirestoreReferences.USERNAME_FIELD,
                name.text.toString()
            ).whereEqualTo(
                MyFirestoreReferences.PASSWORD_FIELD,
                password.text.toString()
            ).addSnapshotListener { snapshot, _ ->
                if (snapshot != null) {
                    e = snapshot.size()
                    Log.d(TAG, "e=" + e)
                    id = usersRef.document().id

                    if (e == 0) {
                        val toast =
                            Toast.makeText(
                                applicationContext,
                                "User " + name.text.toString() + " does not exist!!! ",
                                Toast.LENGTH_LONG
                            )
                        toast.show()
                    } else {
                        db.collection(MyFirestoreReferences.USERS_COLLECTION)
                            .document(name.text.toString()).get().addOnSuccessListener { document ->
                                if (document != null) {

                                    Log.d(TAG, "ID=" + id)
                                    val intent = Intent(this, Account::class.java)
                                    intent.putExtra("id", id)
                                    startActivity(intent)
                                    finish()
                                }

                            }

                    }
                }
            }

        }
    }
}
