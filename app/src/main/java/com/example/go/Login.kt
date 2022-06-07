package com.example.go


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit


class Login : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var storedVerificationId:String
    lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    var RECORD_REQUEST_CODE = 101

    private fun makeRequest4() {
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.ACCESS_NETWORK_STATE),
            RECORD_REQUEST_CODE)}

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            RECORD_REQUEST_CODE -> {

                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(baseContext," Access Denied",Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(baseContext," Access granted",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        var btn_otp= findViewById<Button>(R.id.btnOTP)
        var btn_back= findViewById<ImageView>(R.id.btn_previous)
        makeRequest4()
        btn_otp.setOnClickListener{
            login()

        }

        btn_back.setOnClickListener {
            val intent = Intent(this, Flashscreen::class.java)
            startActivity(intent)
        }

        // Callback function for Phone Auth
        // Callback function for Phone Auth
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                startActivity(Intent(applicationContext, Slogan::class.java))

            }

            override fun onVerificationFailed(e: FirebaseException) {
                Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken) {

                Log.d("TAG","onCodeSent:$verificationId")
                storedVerificationId = verificationId
                resendToken = token
                var intent = Intent(applicationContext,Otpvalide::class.java)
                intent.putExtra("storedVerificationId",storedVerificationId)
                val mobileNumber=findViewById<EditText>(R.id.phoneNumber)
                var number=mobileNumber.text.toString().trim()
                number="+237"+number
                intent.putExtra("phone",number)
                startActivity(intent)
                finish()
            }
        }
    }
    private fun login() {

        val mobileNumber=findViewById<EditText>(R.id.phoneNumber)
        var number=mobileNumber.text.toString().trim()
        val btn_otp : Button = findViewById(R.id.btnOTP)
        btn_otp.isActivated = false;btn_otp.setBackgroundColor(Color.DKGRAY)
        if(!number.isEmpty()){
            number="+237"+number
            sendVerificationcode (number)
            //btn_otp.setBackgroundResource(R.color.gray)
            btn_otp.isActivated = true;
            btn_otp.setBackgroundColor(Color.BLUE)
        }else{
            btn_otp.isActivated = true;
            Toast.makeText(this,"Enter mobile number", Toast.LENGTH_SHORT).show()
        }
        btn_otp.isActivated = true;
    }

    private fun sendVerificationcode(number: String)  {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(number) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this) // Activity (for callback binding)
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }


}