package com.modcom.simon.babyshop

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.auth.FirebaseAuth
import com.modcom.simon.babyshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        mAuth = FirebaseAuth.getInstance()

    }

    override fun onStart() {
        super.onStart()
        val currentuser =mAuth.currentUser
        if(currentuser ==null){
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }else{
            Toast.makeText(this,"Currently logged in as: ${currentuser.email}",
            Toast.LENGTH_SHORT)

        }
//        val register = Intent(this,RegisterActivity::class.java)
//        startActivity(register)
//        finish()
    }
}