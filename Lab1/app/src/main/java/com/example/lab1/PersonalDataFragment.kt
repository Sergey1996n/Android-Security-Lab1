package com.example.lab1

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class PersonalDataFragment : Fragment(R.layout.personal_data) {
    private lateinit var navController: NavController

    private lateinit var auth: FirebaseAuth

    private lateinit var nameText: TextView
    private lateinit var image: ImageView
    private lateinit var emeilText: TextView
    private lateinit var emailVerified: TextView
    private lateinit var uid: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nameText = view.findViewById(R.id.textName)
        image = view.findViewById(R.id.imageView)
        emeilText = view.findViewById(R.id.textEmail)
        emailVerified = view.findViewById(R.id.textVerificyEmail)
        uid = view.findViewById(R.id.textId)

        val user = Firebase.auth.currentUser
        user?.let {
            nameText.text = user.displayName
            emeilText.text = user.email
            Log.wtf("Проверка Url", user.photoUrl.toString())
            Log.wtf("------------------------- Проверка Имени -------------------------", it.displayName.toString())
            /* Вот тут происходит ерунда */
            // image.setImageURI(user.photoUrl)
            emailVerified.text = user.isEmailVerified.toString()
            uid.text = user.uid
        }
    }
}