package com.example.go

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Flashscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flashscreen)
        val logins = findViewById<Button>(R.id.signIn)
        logins.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
            finish()
        }
    }
}





