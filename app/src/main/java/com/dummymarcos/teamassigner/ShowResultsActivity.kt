package com.dummymarcos.teamassigner

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ShowResultsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_results)

        val peopleList = intent.getSerializableExtra("peopleList") as ArrayList<Person>
        val numTeams = intent.getIntExtra("numTeams", 0)
        Log.d("DebugTag", "people List: $peopleList")

        val resultButton = findViewById<Button>(R.id.resultButton)

        fun createTeams(people: List<Person>, numTeams: Int): List<List<Person>> {
            val teams = MutableList(numTeams) { mutableListOf<Person>() }
            var bestTeams = mutableListOf<List<Person>>()
            var bestBalance = Int.MAX_VALUE

            fun backtrack(currentIndex: Int) {
                if (currentIndex == people.size) {
                    // Calculate the skill balance for this combination of teams
                    val skillLevels = teams.map { team -> team.sumBy { it.skill } }
                    val balance = skillLevels.maxOrNull()!! - skillLevels.minOrNull()!!

                    // If this combination has a better skill balance, update the bestTeams and bestBalance
                    if (balance < bestBalance) {
                        bestTeams.clear()
                        bestTeams.addAll(teams.map { it.toList() })
                        bestBalance = balance
                    }
                    return
                }

                // Try adding the current person to each team and continue backtracking
                for (teamIndex in 0 until numTeams) {
                    teams[teamIndex].add(people[currentIndex])
                    backtrack(currentIndex + 1)
                    teams[teamIndex].remove(people[currentIndex])
                }
            }

            // Start the backtracking process
            backtrack(0)

            return bestTeams
        }





        fun displayResult(teams: List<List<Person>>) {
            val resultTextView = findViewById<TextView>(R.id.resultTextView)

            // Clear any previous text
            resultTextView.text = ""

            // Iterate through each team and display the team information
            for ((index, team) in teams.withIndex()) {
                val teamText = "Team ${index + 1}:\n"

                // Set the team text in the resultTextView
                resultTextView.append(teamText)

                // Add debug log for the team information
                Log.d("DEBUG_TAG", "Team $index - ${team.joinToString(", ") { it.name }}")

                // Iterate through each person in the team and add their details to the text
                for (person in team) {
                    val personText = "Name: ${person.name}, Skill Level: ${person.skill}\n"
                    resultTextView.append(personText)

                    // Add debug log for each person's details
                    Log.d("DEBUG_TAG", "Person - ${person.name}, Skill Level: ${person.skill}")
                }

                // Add a separator between teams
                resultTextView.append("\n")
            }
        }

        resultButton.setOnClickListener {
            val teams = createTeams(peopleList.toList(), numTeams)
            // Display the result in the UI
            displayResult(teams)
        }
    }
}

