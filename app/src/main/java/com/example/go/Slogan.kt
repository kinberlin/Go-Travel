package com.example.go

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Slogan : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slogan)
            val newCountryActivityRequestCode = -1
        var Welcome = findViewById<Button>(R.id.btnOTP)
        Welcome.setOnClickListener{
            val intents = Intent(this, MainActivity::class.java)
            startActivityForResult(intents,-1)
        }
    }
}