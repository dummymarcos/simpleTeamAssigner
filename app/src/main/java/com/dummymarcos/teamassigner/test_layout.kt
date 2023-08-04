package com.dummymarcos.teamassigner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup

class test_layout : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_layout)

        // Inflate the test_layout.xml using the layout inflater
        val inflater = LayoutInflater.from(this)
        val viewRoot = findViewById<ViewGroup>(R.id.testLayout)
        val testView = inflater.inflate(R.layout.activity_test_layout, viewRoot, false)

        // Log or display the inflated view to verify if it's working
        Log.d("TestActivity", "Inflated view: $testView")
        setContentView(testView)
    }
}