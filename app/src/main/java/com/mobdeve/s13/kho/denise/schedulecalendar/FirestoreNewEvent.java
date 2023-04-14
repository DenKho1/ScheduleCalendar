package com.mobdeve.s13.kho.denise.schedulecalendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirestoreNewEvent extends AppCompatActivity {
    private EditText EName;
    private EditText ELocation;
    private EditText EDate;
    private EditText EDesc;
    private EditText EStartTIme;
    private EditText EEndTIme;
    private NumberPicker numberPicker;

    private String Tag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        EName = findViewById(R.id.EName);
        ELocation = findViewById(R.id.ELocation);
        EDate = findViewById(R.id.EDate);
        EDesc = findViewById(R.id.EDesc);
        EStartTIme = findViewById(R.id.EStartTIme);
        EEndTIme = findViewById(R.id.EEndTIme);
        numberPicker = findViewById(R.id.number_picker_priority);


        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.new_event_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveNote() {
        SharedPreferences sp = getSharedPreferences("USERNAME",Context.MODE_PRIVATE);
        String user=sp.getString("NAME_KEY","Anon");

        String name = EName.getText().toString();
        String location = ELocation.getText().toString();
        String date = EDate.getText().toString();
        int prio = numberPicker.getValue();

        CollectionReference eventRef = FirebaseFirestore.getInstance().collection("Event");
        eventRef.add(new FirestoreEvent(user,user,name,date,location,prio));

        Toast.makeText(this,"Event added", Toast.LENGTH_SHORT).show();
        finish();
    }
}

