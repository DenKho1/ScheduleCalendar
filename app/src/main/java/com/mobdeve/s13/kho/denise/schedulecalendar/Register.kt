package com.mobdeve.s13.kho.denise.schedulecalendar

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Register : AppCompatActivity() {
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
                val intent = Intent(this, Account::class.java)
                intent.putExtra("Username",name.text.toString())
                intent.putExtra("Email",email.text.toString())
                intent.putExtra("Mobile",mobile.text.toString())
                startActivity(intent)
            }
        }
    }
}