package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

class LoginActivity : AppCompatActivity() {
    private lateinit var loginEmailEditText: EditText
    private lateinit var loginPasswordEditText: EditText
    private lateinit var loginButton: TextView
    private lateinit var loginGoToSignUpTextView: TextView
    private lateinit var loginForgotPassword: TextView
    private val firebaseAuth = Firebase.auth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
        loginListeners()
    }

    private fun loginListeners() {

        loginPasswordEditText.setOnClickListener {
            loginPasswordEditText.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        }

        loginButton.setOnClickListener {
            val email = loginEmailEditText.text.toString()
            val password = loginPasswordEditText.text.toString()

            if (email.isEmpty()||password.isEmpty()){
                Toast.makeText(this, "email or password is empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task->
                if (task.isSuccessful){
                    startActivity(Intent(this,ProfileActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "something's wrong", Toast.LENGTH_SHORT).show()
                }
            }

        }

        loginGoToSignUpTextView.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
            finish()
        }

        loginForgotPassword.setOnClickListener {
            startActivity(Intent(this,ForgotPasswordActivity::class.java))
        }
    }

    private fun init(){
        loginEmailEditText = findViewById(R.id.loginEmailEditText)
        loginPasswordEditText = findViewById(R.id.loginPasswordEditText)
        loginButton = findViewById(R.id.loginButton)
        loginGoToSignUpTextView = findViewById(R.id.loginGoToSignUpTextView)
        loginForgotPassword = findViewById(R.id.loginForgotPassword)
    }
}