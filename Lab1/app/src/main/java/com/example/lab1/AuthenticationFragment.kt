package com.example.lab1

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthenticationFragment : Fragment(R.layout.authentication) {

    private lateinit var btnSign: Button
    private lateinit var btnCreate: Button
    private lateinit var textEmail: EditText
    private lateinit var textPassword: EditText

    private lateinit var auth: FirebaseAuth

    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSign = view.findViewById(R.id.emailSignInButton)
        btnCreate = view.findViewById(R.id.emailCreateAccountButton)
        textEmail = view.findViewById(R.id.fieldEmail)
        textPassword = view.findViewById(R.id.fieldPassword)
        // Initialize Firebase Auth
        auth = Firebase.auth


        btnSign.setOnClickListener {
            var email = textEmail.text.toString()
            var password = textPassword.text.toString()

            if (email != "" && password != "") {
                auth.signInWithEmailAndPassword(email , password)
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(context, "Красавчик, у тебя получилось!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText( context, "Не, ну у тебя какая-то ерунда", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
            else {
                Toast.makeText( context, "Ты там что-то не заполнил", Toast.LENGTH_SHORT).show()
            }
        }

        btnCreate.setOnClickListener {
            var email = textEmail.text.toString()
            var password = textPassword.text.toString()

            if (email != "" && password != "") {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(context,"Красавчик, у тебя получилось!", Toast.LENGTH_SHORT).show()
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(context, "Не, ну у тебя какая-то ерунда", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
            else {
                Toast.makeText(context, "С такими данными даже пирожки не положены", Toast.LENGTH_SHORT).show()
            }
        }

    }
}