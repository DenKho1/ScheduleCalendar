package com.mobdeve.s13.kho.denise.schedulecalendar

import android.content.Intent
import android.os.Bundle
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
        val usersRef = db.collection(MyFirestoreReferences.USERS_COLLECTION).document(id.toString())

        usersRef.get()
            .addOnSuccessListener { documentSnapshot ->
                val user = documentSnapshot.toObject<Users>()
                if (user != null) {
                    name.text= user.username
                    email.text=user.email
                    mobile.text=user.mobile
                }

            }

       // name.text = intent.getStringExtra("Username")
        //email.text= intent.getStringExtra("Email")
       // mobile.text= intent.getStringExtra("Mobile")
        icon.setImageResource(R.drawable.icon)

        this.recyclerView = findViewById(R.id.accRecycler)
        this.recyclerView.adapter = InviteAdapter(this.inviteList)
        this.recyclerView.layoutManager = LinearLayoutManager(this)


        val register=findViewById<Button>(R.id.AccLogout)

        register.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

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