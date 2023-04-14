package com.mobdeve.s13.kho.denise.schedulecalendar

import android.content.Context
import android.content.Intent
import android.os.Bundle
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
        val date=findViewById<EditText>(R.id.ELocation)


        val sp1=getSharedPreferences("EVENT_DETAILS", Context.MODE_PRIVATE)
        val sp2=getSharedPreferences("USERNAME",Context.MODE_PRIVATE)

        val title=sp1.getString("EVENT_NAME_KEY","N/A")
        val loc=sp1.getString("EVENT_LOCATION_KEY","N/A")
        val time =sp1.getString("EVENT_DATE_KEY","N/A")
        val user=sp2.getString("NAME_KEY", "Anon")

        val db = FirebaseFirestore.getInstance()
        val eventRef=db.collection("Event")
        val query= eventRef.whereEqualTo("lnameTxt",title)
            .whereEqualTo("ldateTxt",time)
            .whereEqualTo("llocationTxt",loc)
            .whereEqualTo("user",user).get()
            .addOnSuccessListener {documents->
        if(!documents.isEmpty)
        {
            val document=documents.first()
            name.setText(document.data["LNameTxt"].toString())
            location.setText(document.data["LLocationTxt"].toString())
            date.setText(document.data["LDateTxt"].toString())

        }

        this.recyclerView = findViewById(R.id.invRecyclerView)
        this.recyclerView.adapter = HostInviteAdapter(this.hostInviteList)
        this.recyclerView.layoutManager = LinearLayoutManager(this)

        edit.setOnClickListener() {
            val intent = Intent(this, EditEvent::class.java)
            startActivity(intent)
        }

        send.setOnClickListener() {
            val intent = Intent(this, EventDetailsHostSendInv::class.java)
            startActivity(intent)
        }
    }
}
}