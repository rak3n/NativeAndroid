package com.example.firstkotlin
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter;
import android.widget.Spinner;

class SpinnerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner)
        val country = resources.getStringArray(R.array.country)
        var spinner = findViewById<Spinner>(R.id.spinner)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, country)
        spinner.adapter = adapter
    }
}