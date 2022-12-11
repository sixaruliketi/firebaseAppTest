package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ChangePasswordActivity : AppCompatActivity() {
    private lateinit var changePasswordGoBackButton: ImageView
    private lateinit var changePasswordCurrentPasswordEditText: EditText
    private lateinit var changePasswordNewPasswordEditText: EditText
    private lateinit var changePasswordButton: TextView
    private val firebaseAuth = Firebase.auth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
        init()
        changePasswordListeners()
    }

    private fun changePasswordListeners() {
        changePasswordGoBackButton.setOnClickListener {
            goToProfile()
        }
        changePasswordNewPasswordEditText.setOnClickListener {
            changePasswordNewPasswordEditText.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        }
        changePasswordButton.setOnClickListener {
            val currentPassword = changePasswordCurrentPasswordEditText.text.toString()
            val newPassword = changePasswordNewPasswordEditText.text.toString()
            if (newPassword==currentPassword){
                Toast.makeText(this, "use other password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (newPassword.isEmpty()){
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            firebaseAuth.currentUser?.updatePassword(newPassword)?.addOnCompleteListener { task ->
                if(task.isSuccessful){
                    Toast.makeText(this, "password updated", Toast.LENGTH_SHORT).show()
                    goToProfile()
                } else {
                    Toast.makeText(this, "something's wrong", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun goToProfile(){
        startActivity(Intent(this,ProfileActivity::class.java))
        finish()
    }

    private fun init(){
        changePasswordGoBackButton = findViewById(R.id.changePasswordGoBackButton)
        changePasswordCurrentPasswordEditText = findViewById(R.id.changePasswordCurrentPasswordEditText)
        changePasswordNewPasswordEditText = findViewById(R.id.changePasswordNewPasswordEditText)
        changePasswordButton = findViewById(R.id.changePasswordButton)

    }
}