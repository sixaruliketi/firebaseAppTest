package com.example.myapplication

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UserInfoFragment : Fragment(R.layout.fragment_user_info) {
    private lateinit var goBackButton: ImageView
    private lateinit var userImageView: ImageView
    private lateinit var userInfoName: TextView
    private lateinit var userInfoLastName: TextView
    private lateinit var userInfoAge: TextView
    private lateinit var userInfoId: TextView

//    private val db = Firebase.database.reference
//    val auth = FirebaseAuth.getInstance()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        goBackButton.setOnClickListener {
            onDetach()
        }
    }

    private fun init() {
        goBackButton = requireView().findViewById(R.id.goBackButton)
        userImageView = requireView().findViewById(R.id.userImageView)
        userInfoName = requireView().findViewById(R.id.userInfoName)
        userInfoLastName = requireView().findViewById(R.id.userInfoLastName)
        userInfoAge = requireView().findViewById(R.id.userInfoAge)
        userInfoId = requireView().findViewById(R.id.userInfoId)
    }
}