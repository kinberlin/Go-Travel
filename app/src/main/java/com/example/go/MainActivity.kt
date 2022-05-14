package com.example.go

import android.Manifest
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.bundleOf
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.go.Models.Town
import com.example.go.activity.Passenger_Activity
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var mDrawer: DrawerLayout
    lateinit var nvDrawer: NavigationView

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.sidemenunav, menu)
        return super.onCreateOptionsMenu(menu)
    }
    private fun makeRequest1() {
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            101)}
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.menu_logout ->{
                auth.signOut()
                startActivity(Intent(this,Flashscreen::class.java), bundleOf())
                //finish()
            }

            R.id.menu_contact ->{
                startActivity(Intent(this,Passenger_Activity::class.java), bundleOf())
                }

            R.id.menu_myBooking ->{
                    startActivity(Intent(this,Booking_history::class.java), bundleOf())

                }
            R.id.menu_Book -> {
                    startActivity(Intent(this,reservation_search::class.java), bundleOf())
                }
            }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makeRequest1()
        setContentView(R.layout.activity_main)

        val navigationView: NavigationView = findViewById(R.id.navigationview)

        // var toolbar = findViewById<Toolbar>(R.id.toolbar)

        // Set a Toolbar to replace the ActionBar.
        //setSupportActionBar(toolbar)

        // This will display an Up icon (<-), we will replace it with hamburger later
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        // Find our drawer view
        mDrawer = findViewById(R.id.drawableLayout)

        nvDrawer = findViewById(R.id.navigationview)
        // Setup drawer view
        setupDrawerContent(nvDrawer)


            auth = FirebaseAuth.getInstance()
        var currentuser = auth.currentUser
            val DefaultMain = HomePage()
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.idFragments, DefaultMain)
                commit()

        }

        /*val from = findViewById<EditText>(R.id.from_input).text.toString()
        val to = findViewById<EditText>(R.id.To_input).text.toString()
        val date = findViewById<CalendarView>(R.id.date_input).date.toString()*/
    }
    fun signoout(){
        auth= FirebaseAuth.getInstance()
        auth.signOut()
        LoginStart()
    }
    fun LoginStart()
    {
        startActivity(Intent(this,Flashscreen::class.java))
        finish()
    }

    private fun setupDrawerContent(navigationView: NavigationView) {
        navigationView.setNavigationItemSelectedListener { menuItem ->
            selectDrawerItem(menuItem)
            true
        }
    }

    fun selectDrawerItem(menuItem: MenuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        //var fragment: Fragment? = null
        //val fragmentClass: Class<*>
        //fragmentClass =
            when (menuItem.itemId) {
            R.id.menu_contact -> Toast.makeText(applicationContext, "Not More Available ...", Toast.LENGTH_LONG).show()//Passenger_Activity::class.java
            R.id.menu_myBooking  -> { Toast.makeText(applicationContext, "Getting bookings ...", Toast.LENGTH_LONG).show()
                startActivity(Intent(this,Booking_history::class.java), bundleOf())
            }
            R.id.menu_Book ->  {Toast.makeText(applicationContext, "Resultats en cours de chargement ...", Toast.LENGTH_LONG).show()
                startActivity(Intent(this,MainActivity::class.java), bundleOf()) }

                R.id.menu_logout -> {auth.signOut()
                    Toast.makeText(applicationContext, "Your session is closed ...", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this,Flashscreen::class.java), bundleOf())}
                R.id.menu_Shared -> {
                    Toast.makeText(applicationContext, "About Us", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,aboutUs_popup::class.java), bundleOf())}

                else -> Toast.makeText(applicationContext, "Not More Available ...", Toast.LENGTH_LONG).show()
        }
    }

}