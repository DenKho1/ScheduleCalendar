package com.mobdeve.s13.kho.denise.schedulecalendar

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Register : AppCompatActivity() {
    companion object {
        private const val TAG = "LoginActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val register = findViewById<Button>(R.id.RegisterButton)

        register.setOnClickListener {

            val name = findViewById<EditText>(R.id.Rusername)
            val password = findViewById<EditText>(R.id.Rpassword)
            val cpassword = findViewById<EditText>(R.id.Rconfirmpassword)
            val email = findViewById<EditText>(R.id.Remail)
            val mobile = findViewById<EditText>(R.id.Rmobilenum)
            val pass1=password.text.toString()
            val pass2=cpassword.text.toString()
            if(name.text.isEmpty() || password.text.isEmpty() || cpassword.text.isEmpty() || email.text.isEmpty() || mobile.text.isEmpty())
             {
                val toast=Toast.makeText(applicationContext,"Something is missing!",Toast.LENGTH_LONG)
                 toast.show()
             }
            //passwords dont match
            else if(!pass1.equals(pass2)) {
                val pass=password.text
                pass.append(" ")
                pass.append(cpassword.text)

                val toast=Toast.makeText(applicationContext,"Your Passwords' Do Not Match !",Toast.LENGTH_LONG)
                toast.show()
            }

            else
            {
                val db=Firebase.firestore
                val usersRef=db.collection(MyFirestoreReferences.USERS_COLLECTION)

                val query=usersRef.whereEqualTo(
                    MyFirestoreReferences.USERNAME_FIELD,
                    name.toString()
                )

                query.get().addOnCompleteListener{
                    task->
                    if(task.isSuccessful){
                        if(task.result.isEmpty){
                            //add to db
                            val data: MutableMap<String,Any> = HashMap()
                            data[MyFirestoreReferences.USERNAME_FIELD]=name.text.toString()
                            data[MyFirestoreReferences.PASSWORD_FIELD]=pass1
                            data[MyFirestoreReferences.EMAIL_FIELD]=email.text.toString()
                            data[MyFirestoreReferences.MOBILE_FIELD]=mobile.text.toString()
                            usersRef
                                .add(data)
                                .addOnSuccessListener { documentReference ->
                                    Log.d(TAG,"DocumentSnapshot written with ID: "+ documentReference.id)
                                    //move to next activity
                                    val intent = Intent(this, Account::class.java)
                                    intent.putExtra("id",documentReference.id)
                                    startActivity(intent)
                                }
                                .addOnFailureListener{
                                    e ->Log.w(TAG,"Error adding document", e)}
                                }
                        }

                        else
                        {
                            val toast=Toast.makeText(applicationContext,"Username is already taken!!!",Toast.LENGTH_LONG)
                            toast.show()
                        }
                    }
                }

            }
        }
    }
