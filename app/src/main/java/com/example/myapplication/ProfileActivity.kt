package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfileActivity : AppCompatActivity() {
    private lateinit var profileLogOutButton : TextView
    private lateinit var profileChangePasswordButton: TextView
    private val firebaseAuth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        init()
        profileListeners()
    }

    private fun profileListeners() {
        profileLogOutButton.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
        profileChangePasswordButton.setOnClickListener {
            startActivity(Intent(this,ChangePasswordActivity::class.java))
        }
    }

    private fun init(){
        profileLogOutButton = findViewById(R.id.profileLogOutButton)
        profileChangePasswordButton = findViewById(R.id.profileChangePasswordButton)
    }
}