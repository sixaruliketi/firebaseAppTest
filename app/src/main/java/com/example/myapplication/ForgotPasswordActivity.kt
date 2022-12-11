package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var forgotPasswordGoBackButton: ImageView
    private lateinit var forgotPasswordEmailEditText: EditText
    private lateinit var forgotPasswordSendTextView: TextView
    private val firebaseAuth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        init()
        forgotPasswordListeners()
    }

    private fun forgotPasswordListeners() {
        forgotPasswordGoBackButton.setOnClickListener {
            goToLoginActivity()
        }
        forgotPasswordSendTextView.setOnClickListener {
            val email = forgotPasswordEmailEditText.text.toString()

            if (email.isEmpty()){
                return@setOnClickListener
            }

            firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                if (task.isSuccessful){
                    Toast.makeText(this, "check your email!", Toast.LENGTH_SHORT).show()
                    goToLoginActivity()
                } else {
                    Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
    private fun goToLoginActivity(){
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
    private fun init(){
        forgotPasswordGoBackButton = findViewById(R.id.forgotPasswordGoBackButton)
        forgotPasswordEmailEditText = findViewById(R.id.forgotPasswordEmailEditText)
        forgotPasswordSendTextView = findViewById(R.id.forgotPasswordSendTextView)
    }
}