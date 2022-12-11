package com.example.myapplication

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.voice.VoiceInteractionSession
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ProfileActivity : AppCompatActivity() {
    private lateinit var profileLogOutButton : TextView
    private lateinit var profileChangePasswordButton: TextView
    private val firebaseAuth = Firebase.auth

    private lateinit var profileUsernameTextView : TextView
    private lateinit var profileEditImageButton: ImageView
    private lateinit var avatarUrlEditText: EditText
    private lateinit var profileImageView : ImageView
    private lateinit var profileChangeProfileAvatarButton : TextView
    private lateinit var profileCloseUrlButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        init()
        profileListeners()
    }

    private fun profileListeners() {

        profileEditImageButton.setOnClickListener {
            avatarUrlEditText.visibility = VISIBLE
            profileCloseUrlButton.visibility = VISIBLE
        }

        profileCloseUrlButton.setOnClickListener {
            avatarUrlEditText.visibility = INVISIBLE
            profileCloseUrlButton.visibility = INVISIBLE
        }

        profileChangeProfileAvatarButton.setOnClickListener {
            val url = avatarUrlEditText.text.toString()
            Glide.with(applicationContext).load(url).into(profileImageView)
        }

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
        profileEditImageButton = findViewById(R.id.profileEditImageButton)
        profileImageView = findViewById(R.id.profileImageView)
        avatarUrlEditText = findViewById(R.id.avatarUrlEditText)
        profileChangeProfileAvatarButton = findViewById(R.id.profileChangeProfileAvatarButton)
        profileCloseUrlButton = findViewById(R.id.profileCloseUrlButton)
        profileUsernameTextView = findViewById(R.id.profileUsernameTextView)
        profileUsernameTextView.text = firebaseAuth.currentUser?.uid
    }
}