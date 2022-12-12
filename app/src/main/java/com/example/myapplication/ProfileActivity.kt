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
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserInfo
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


class ProfileActivity : AppCompatActivity() {
    private lateinit var profileLogOutButton : TextView
    private lateinit var profileChangePasswordButton: TextView
    private val firebaseAuth = Firebase.auth

    private lateinit var profileUsernameTextView : TextView
    private lateinit var profileImageView : ImageView
    private lateinit var profileChangeProfileAvatarButton : TextView
    lateinit var urlEditText :EditText
    lateinit var usernameEditText:EditText

    private val db = FirebaseDatabase.getInstance().getReference("userInfo")
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        init()
        profileListeners()

        db.child(auth.currentUser?.uid!!).addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val userInfo : User = snapshot.getValue(User::class.java) ?: return
                profileUsernameTextView.text = userInfo.username
                Glide.with(this@ProfileActivity).load(userInfo.url).into(profileImageView)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })



    }

    private fun profileListeners() {

        profileChangeProfileAvatarButton.setOnClickListener {
            val url = urlEditText.text.toString()
            val username = usernameEditText.text.toString()
            val userInfo = User(username,url)
            db.child(auth.currentUser?.uid!!).setValue(userInfo)
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
        profileImageView = findViewById(R.id.profileImageView)
        profileChangeProfileAvatarButton = findViewById(R.id.profileChangeProfileAvatarButton)
        profileUsernameTextView = findViewById(R.id.profileUsernameTextView)

        urlEditText = findViewById(R.id.urlEditText)
        usernameEditText=findViewById(R.id.userNameEditText)

    }
}