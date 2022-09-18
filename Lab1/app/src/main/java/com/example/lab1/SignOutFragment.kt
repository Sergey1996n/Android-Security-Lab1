package com.example.lab1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
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
            if (Firebase.auth.currentUser == null)
                Toast.makeText( context, "Он ушёл, но обещал вернуться", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText( context, "Хм... А я и так тебя не знаю!!!", Toast.LENGTH_SHORT).show()
        }
    }
}