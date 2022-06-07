package com.example.go

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.go.Models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_otpvalide.*

class Otpvalide : AppCompatActivity() {
    lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otpvalide)

        auth=FirebaseAuth.getInstance()

        val storedVerificationId=intent.getStringExtra("storedVerificationId")
    val phonenumber = intent.getStringExtra("phone")
        var btnotp = findViewById<Button>(R.id.btnOTP)
        val otpGiven=findViewById<EditText>(R.id.id_otp)
        Toast.makeText(this, "Create an account to continue", Toast.LENGTH_SHORT).show()
        var user = User(phonenumber ?: " "," "," ")
        var userlist = user.Authenticate(phonenumber ?: " ")
        Thread.sleep(1000L);
        if(userlist.isEmpty()){
            var intent = Intent(applicationContext, Signup::class.java)
            intent.putExtra("phone",phonenumber)
            startActivity(intent)
            finish()
        }
        btnotp.setOnClickListener {
            var otp = otpGiven.text.toString()
            if(!otp.isEmpty()){
                val credential : PhoneAuthCredential = PhoneAuthProvider.getCredential(
                    storedVerificationId.toString(), otp)
                signInWithPhoneAuthCredential(credential,phonenumber ?: "" )

            }else{
                Toast.makeText(this,"Enter OTP",Toast.LENGTH_SHORT).show()
            }
        }
        inscrirebtn_.setOnClickListener {

        }
        Thread.sleep(500L)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential,phonenumber : String) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    try {

                        var user = User(phonenumber ?: " ", " ", " ")
                        var te = user.Authenticate(phonenumber ?: " " ).isEmpty()
                        if(te){
                            Toast.makeText(this, "Succesfull", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(applicationContext, Slogan::class.java))
                            finish()
                            //user.postUser();
                        }else{
                            Toast.makeText(this, "Vous n'etes pas encore inscrit!", Toast.LENGTH_SHORT).show()
                        }

                    }catch (e : Exception){Toast.makeText(applicationContext,e.message + " "+ e.localizedMessage, Toast.LENGTH_LONG).show()}

                        //Toast.makeText(this, "Create an account to continue", Toast.LENGTH_SHORT).show()
                       // var user = User(auth.currentUser!!.phoneNumber!!)
// ...
                } else {
// Sign in failed, display a message and update the UI
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
// The verification code entered was invalid
                        Toast.makeText(this, "Invalid OTP", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }
}