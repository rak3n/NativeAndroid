package com.example.firstkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailsActivity : AppCompatActivity() {
    private lateinit var nameTextView:TextView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        var bundle = intent.extras
        init()
        nameTextView.setText(bundle?.getString("name"))
    }

    fun init(){
        nameTextView = findViewById<TextView>(R.id.detailsPageName)
    }
}