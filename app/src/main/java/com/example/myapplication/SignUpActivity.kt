package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

class SignUpActivity : AppCompatActivity() {

    private lateinit var signupEmailEditText: EditText
    private lateinit var signupPasswordEditText: EditText
    private lateinit var signupRepeatPasswordEditText: EditText
    private lateinit var signupButton: TextView
    private lateinit var signupGoToLogin: TextView

    private val firebaseAuth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        init()
        signupListeners()
    }

    private fun signupListeners() {
        signupButton.setOnClickListener {
            val email = signupEmailEditText.text.toString()
            val password = signupPasswordEditText.text.toString()
            val repeatPassword = signupRepeatPasswordEditText.text.toString()

            if(repeatPassword!=password){
                Toast.makeText(this, "wrong password!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (email.isEmpty() || password.isEmpty()){
                return@setOnClickListener
            }

            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful){
                    Toast.makeText(this, "congrats! now login!", Toast.LENGTH_SHORT).show()
                    goToLogin()
                } else {
                    Toast.makeText(this, "something's wrong, try again", Toast.LENGTH_SHORT).show()
                }
            }

        }
        signupGoToLogin.setOnClickListener {
            goToLogin()
        }
    }

    private fun goToLogin(){
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun init(){
        signupEmailEditText = findViewById(R.id.signupEmailEditText)
        signupPasswordEditText = findViewById(R.id.signupPasswordEditText)
        signupRepeatPasswordEditText = findViewById(R.id.signupRepeatPasswordEditText)
        signupButton = findViewById(R.id.signupButton)
        signupGoToLogin = findViewById(R.id.signupGoToLogin)
    }

}