package com.dummymarcos.teamassigner

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity



class activity_input_people : AppCompatActivity() {

    private lateinit var peopleList: MutableList<Person>
    private lateinit var container: LinearLayout
    private lateinit var editTextList: MutableList<EditText>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_people)

        val numTeams = intent.getIntExtra("numTeams", 0)
        val numPeople = intent.getIntExtra("numPeople_extra", 0)
        Log.d("Tag", "numPeople: $numPeople")
        peopleList = mutableListOf()
        container = findViewById(R.id.container)
        editTextList = mutableListOf()

        generateEditTextFields(numPeople)

        val submitButton = findViewById<Button>(R.id.submitButton)

        submitButton.setOnClickListener {
            retrievePeopleData(numPeople)
            // Do something with the populated peopleList
            val intent = Intent(this, ShowResultsActivity::class.java)
            intent.putExtra("peopleList", ArrayList(peopleList))
            intent.putExtra("numTeams", numTeams)
            startActivity(intent)
        }
    }

    private fun generateEditTextFields(numPeople: Int) {
        LayoutInflater.from(this)
        val container = findViewById<ViewGroup>(R.id.container)

        for (i in 0 until numPeople) {
            val personLayout = LinearLayout(this)
            personLayout.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            )
            personLayout.orientation = LinearLayout.HORIZONTAL

            val nameEditText = EditText(this)
            nameEditText.layoutParams = LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1f
            )
            nameEditText.hint = getString(R.string.enter_name)
            nameEditText.inputType = InputType.TYPE_CLASS_TEXT

            val skillEditText = EditText(this)
            skillEditText.layoutParams = LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1f
            )
            skillEditText.hint = getString(R.string.enter_skill_level)
            skillEditText.inputType = InputType.TYPE_CLASS_NUMBER

            personLayout.addView(nameEditText)
            personLayout.addView(skillEditText)
            container.addView(personLayout)
            editTextList.add(nameEditText)
            editTextList.add(skillEditText)
        }
    }



    private fun retrievePeopleData(numPeople: Int) {
        peopleList.clear()

        for (i in 0 until numPeople) {
            val nameEditText = editTextList[i * 2] // Retrieve the name EditText
            val skillEditText = editTextList[i * 2 + 1] // Retrieve the skill EditText

            val name = nameEditText.text.toString()
            val skillString = skillEditText.text.toString()
            val skill = if (skillString.isNotEmpty()) skillString.toIntOrNull() ?: 0 else 0

            val person = Person(name, skill)
            peopleList.add(person)
        }
    }
}