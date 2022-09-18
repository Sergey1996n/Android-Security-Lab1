package com.example.lab1

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignOutFragment: Fragment(R.layout.sign_out) {
    private lateinit var navController: NavController

    private lateinit var auth: FirebaseAuth

    private lateinit var btnDelete: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnDelete = view.findViewById(R.id.btn_del_acc)

        btnDelete.setOnClickListener {
            Firebase.auth.signOut()
        }
    }
}