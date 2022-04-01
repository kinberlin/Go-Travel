package com.example.go

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            val DefaultMain = HomePage()
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.idFragments, DefaultMain)
                commit()

        }
    }
    fun LoginStart()
    {
        startActivity(Intent(this,Flashscreen::class.java))
        finish()
    }
}