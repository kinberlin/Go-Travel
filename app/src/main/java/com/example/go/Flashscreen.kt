package com.example.go

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.go.Models.User
import com.google.firebase.auth.FirebaseAuth

class Flashscreen : AppCompatActivity() {
    var RECORD_REQUEST_CODE = 101
    private fun makeRequest3() {
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.INTERNET),
            RECORD_REQUEST_CODE)}
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            RECORD_REQUEST_CODE -> {

                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(baseContext," Access Denied", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(baseContext," Access granted", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flashscreen)
        val logins = findViewById<Button>(R.id.signIn)
        makeRequest3()
        var auth : FirebaseAuth
            auth= FirebaseAuth.getInstance()
            var currentUser = auth.currentUser

            var user  = User()
//            var te = user.Authenticate(currentUser!!.phoneNumber!! ?: " " )
            //var tet = te.isEmpty()
           // if(!tet) {
                if (currentUser != null) {
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    finish()
                }

           // }
        logins.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
            finish()
        }
        Thread.sleep(5000L)
    }
}





