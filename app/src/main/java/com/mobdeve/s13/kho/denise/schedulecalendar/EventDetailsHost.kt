package com.mobdeve.s13.kho.denise.schedulecalendar

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
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
        d
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
        }

        this.recyclerView = findViewById(R.id.invRecyclerView)
        this.recyclerView.adapter = HostInviteAdapter(this.hostInviteList)
        this.recyclerView.layoutManager = LinearLayoutManager(this)

        edit.setOnClickListener() {
            val editquery = eventRef.whereEqualTo("lnameTxt",title)
                .whereEqualTo("ldateTxt",time).limit
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
                    }
            db.runBatch { batch ->
                editquery.get()
                    .addOnSuccessListener { querySnapshot ->
                        if (!querySnapshot.isEmpty) {
                            val docRef = db.collection("myCollection").document(querySnapshot.documents[0].id)
                            batch.update(docRef, "field1", "new value")
                            batch.update(docRef, "field2", 42)
                            batch.commit()
                                .addOnSuccessListener {
                                    Log.d(TAG, "Batch update successful!")
                                }
                                .addOnFailureListener { e ->
                                    Log.w(TAG, "Error updating document", e)
                                }
                        } else {
                            Log.d(TAG, "No documents matched the query.")
                        }
                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error getting documents", e)
                    }
            }
        }

        send.setOnClickListener() {
            val intent = Intent(this, EventDetailsHostSendInv::class.java)
            startActivity(intent)
        }
    }
}
}