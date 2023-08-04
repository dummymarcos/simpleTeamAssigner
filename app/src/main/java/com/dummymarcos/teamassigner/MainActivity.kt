package com.dummymarcos.teamassigner

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.os.Build
import android.util.Log




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val peopleEdit = findViewById<EditText>(R.id.editTextNumberDecimalPeople)
        val teamsEdit = findViewById<EditText>(R.id.editTextNumberDecimalTeams)
        val buttonNext = findViewById<Button>(R.id.buttonNext)
        // Log the Android version to the Logcat
        Log.d("Tag", "Android version: ${Build.VERSION.RELEASE}")


        buttonNext.setOnClickListener {
            val numPeople = peopleEdit.text.toString().toIntOrNull() ?: 0
            val numTeams = teamsEdit.text.toString().toIntOrNull() ?: 0
            val intent = Intent(this, activity_input_people::class.java)
            intent.putExtra("numPeople_extra", numPeople)
            intent.putExtra("numTeams", numTeams)
            startActivity(intent)
        }
    }
}
