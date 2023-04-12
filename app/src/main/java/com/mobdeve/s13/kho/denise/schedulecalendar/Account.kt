package com.mobdeve.s13.kho.denise.schedulecalendar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject


class Account : AppCompatActivity() {

    companion object {
        private const val TAG = "AccountActivity"
        private const val NAME_KEY="NAME_KEY"
    }

    private val inviteList: ArrayList<Invite> = InviteGenerator.genData()
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        val name = findViewById<TextView>(R.id.AccUsername)
        val email = findViewById<TextView>(R.id.accEmail)
        val mobile = findViewById<TextView>(R.id.accMobile)

        val icon=findViewById<ImageView>(R.id.icon)
        val id=intent.getStringExtra("id")

        val db = FirebaseFirestore.getInstance()

        val sp=getSharedPreferences("USERNAME",Context.MODE_PRIVATE)
        val named=sp.getString("NAME_KEY","Anon")
        if(named!=null) {
            Log.d(TAG,"name="+named)

            val usersRef = db.collection(MyFirestoreReferences.USERS_COLLECTION).whereEqualTo(
                MyFirestoreReferences.USERNAME_FIELD,
                named
            )

            usersRef.get().addOnSuccessListener { documents->
                if(!documents.isEmpty)
                {
                    val document=documents.first()
                    name.text=document.data[MyFirestoreReferences.USERNAME_FIELD].toString()
                    email.text=document.data[MyFirestoreReferences.EMAIL_FIELD].toString()
                    mobile.text=document.data[MyFirestoreReferences.MOBILE_FIELD].toString()
                }

            }.addOnFailureListener{exception->
                Log.d(TAG,"Error getting documents: ",exception)

            }
        }
        icon.setImageResource(R.drawable.icon)

        this.recyclerView = findViewById(R.id.accRecycler)
        this.recyclerView.adapter = InviteAdapter(this.inviteList)
        this.recyclerView.layoutManager = LinearLayoutManager(this)


        val logout=findViewById<Button>(R.id.AccLogout)


        logout.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            val sp=this.getSharedPreferences("USERNAME", Context.MODE_PRIVATE)
            val edit=sp.edit()
            edit.clear()
            startActivity(intent)
            finish()
        }

        val sched=findViewById<ImageButton>(R.id.accsched)

        sched.setOnClickListener {
            val intent = Intent(this, FirestoreEventSchedule::class.java)
            val id1=intent.getStringExtra("id")
            intent.putExtra("id",id1)
            startActivity(intent)
        }

    }
}