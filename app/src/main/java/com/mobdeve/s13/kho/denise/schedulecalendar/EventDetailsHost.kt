package com.mobdeve.s13.kho.denise.schedulecalendar

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class EventDetailsHost : AppCompatActivity() {
    private val hostInviteList: ArrayList<HostInvite> = HostInviteGenerator.genData()
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details_host)
        val edit = findViewById<Button>(R.id.EditEBtn)
        val send = findViewById<Button>(R.id.SendInvitesBtn)

        val name =findViewById<EditText>(R.id.EName)
        val location=findViewById<EditText>(R.id.ELocation)
        val date=findViewById<EditText>(R.id.EDate)
        val desc=findViewById<EditText>(R.id.EDesc)
        val start=findViewById<EditText>(R.id.EStartTIme)
        val end=findViewById<EditText>(R.id.EEndTIme)


        val sp1=getSharedPreferences("EVENT_DETAILS", Context.MODE_PRIVATE)
        val sp2=getSharedPreferences("USERNAME",Context.MODE_PRIVATE)

        val title=sp1.getString("EVENT_NAME_KEY","N/A")
        val loc=sp1.getString("EVENT_LOCATION_KEY","N/A")
        val time =sp1.getString("EVENT_DATE_KEY","N/A")
        val user=sp2.getString("NAME_KEY", "Anon")

        val db = FirebaseFirestore.getInstance()

        val eventRef= db.collection("Event")
        val query = eventRef.whereEqualTo("lnameTxt",title)
            .whereEqualTo("ldateTxt",time)
            .whereEqualTo("llocationTxt",loc)
            .whereEqualTo("user",user).get()
            .addOnSuccessListener {documents->
        if(!documents.isEmpty)
        {
            val document=documents.first()
            name.setText(document.data["lnameTxt"].toString())
            location.setText(document.data["llocationTxt"].toString())
            date.setText(document.data["ldateTxt"].toString())
            desc.setText(document.data["edesc"].toString())
            start.setText(document.data["estart"].toString())
            end.setText(document.data["eend"].toString())

            val sp = getSharedPreferences("HOST", MODE_PRIVATE)
            sp.edit().putString("HOST_KEY", document.data["host"].toString()).apply()

        }

        this.recyclerView = findViewById(R.id.invRecyclerView)
        this.recyclerView.adapter = HostInviteAdapter(this.hostInviteList)
        this.recyclerView.layoutManager = LinearLayoutManager(this)

        edit.setOnClickListener() {
            val sp = getSharedPreferences("HOST", MODE_PRIVATE)
            val host=sp.getString("HOST_KEY","N/A")
            val sp2 = getSharedPreferences("USERNAME", MODE_PRIVATE)
            val named=sp2.getString("NAME_KEY","Anon")

            if(host.equals(named)) {
                val eventRef = db.collection("Event")
                val query = eventRef.whereEqualTo("lnameTxt", title)
                    .whereEqualTo("ldateTxt", time)
                    .whereEqualTo("llocationTxt", loc)
                    .whereEqualTo("user", user).limit(1);

                query.get()
                    .addOnSuccessListener { querySnapshot ->
                        if (!querySnapshot.isEmpty) {
                            val docRef =
                                db.collection("Event").document(querySnapshot.documents[0].id)
                            docRef.update("lnameTxt", name.text.toString()).addOnSuccessListener {
                                Log.d(TAG, "Name Update successful!")
                            }
                            docRef.update("llocationTxt", location.text.toString())
                                .addOnSuccessListener {
                                    Log.d(TAG, "Location Update successful!")
                                }
                            docRef.update("ldateTxt", date.text.toString()).addOnSuccessListener {
                                Log.d(TAG, "Date Update successful!")
                            }
                            docRef.update("edesc", desc.text.toString()).addOnSuccessListener {
                                Log.d(TAG, "Desc Update successful!")
                            }
                            docRef.update("estart", start.text.toString()).addOnSuccessListener {
                                Log.d(TAG, "Start Update successful!")
                            }
                            docRef.update("eend", end.text.toString()).addOnSuccessListener {
                                Log.d(TAG, "End Update successful!")
                            }
                        }
                    }
                Toast.makeText(this, "Update Successful!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, FirestoreEventSchedule::class.java)
                startActivity(intent)
            }

            else
            {
                Toast.makeText(this, "You Cannot Edit this Event", Toast.LENGTH_SHORT).show()
            }

        }


        send.setOnClickListener() {
            val intent = Intent(this, FirestoreNewInvite::class.java)
            startActivity(intent)
        }
    }
}
}