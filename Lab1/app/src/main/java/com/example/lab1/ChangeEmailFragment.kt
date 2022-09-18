package com.example.lab1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ChangeEmailFragment : Fragment(R.layout.change_email) {
    private lateinit var btnChangeEmail: Button
    private lateinit var textEmeil: EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /** Инициализируем наши вьюшки */
        btnChangeEmail = view.findViewById(R.id.btn_change)
        textEmeil = view.findViewById(R.id.editTextEmail)

        btnChangeEmail.setOnClickListener {
            val user = Firebase.auth.currentUser
            val emeil = textEmeil.text.toString()

            if (user != null && emeil != "") {
                user.updateEmail(emeil)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText( context, "У тебя получилось!!!", Toast.LENGTH_SHORT).show()
                        }
                        else{
                            Toast.makeText( context, "Произошла ерунда.", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }
}