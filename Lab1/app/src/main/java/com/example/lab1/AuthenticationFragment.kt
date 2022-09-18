package com.example.lab1

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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthenticationFragment : Fragment(R.layout.authentication) {
    private lateinit var navController: NavController

    private lateinit var btnSign: Button
    private lateinit var btnCreate: Button
    private lateinit var textEmail: EditText
    private lateinit var textPassword: EditText

    private lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view);

        btnSign = view.findViewById(R.id.emailSignInButton)
        btnCreate = view.findViewById(R.id.emailCreateAccountButton)
        textEmail = view.findViewById(R.id.fieldEmail)
        textPassword = view.findViewById(R.id.fieldPassword)
        // Initialize Firebase Auth
        auth = Firebase.auth

        btnSign.setOnClickListener {
            auth.signInWithEmailAndPassword(textEmail.text.toString(), textPassword.text.toString())
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = auth.currentUser
                            Toast.makeText(
                                context, "Красавчик, у тебя получилось!",
                                Toast.LENGTH_SHORT
                            ).show()
                        //updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(
                            context, "Не, ну у тебя какая-то ерунда",
                            Toast.LENGTH_SHORT
                        ).show()
                        //updateUI(null)
                    }
                }
        }
            btnCreate.setOnClickListener {
                auth.createUserWithEmailAndPassword(
                    textEmail.text.toString(),
                    textPassword.text.toString()
                )
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            val user = auth.currentUser
                                Toast.makeText(
                                    context,
                                    "Красавчик, у тебя получилось!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            //updateUI(user)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(
                                context, "Не, ну у тебя какая-то ерунда",
                                Toast.LENGTH_SHORT
                            ).show()
                            //updateUI(null)
                        }
                    }
            }

    }



}