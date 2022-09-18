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
    private lateinit var textEmail: EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = Firebase.auth.currentUser

        btnChangeEmail = view.findViewById(R.id.btn_change)
        textEmail = view.findViewById(R.id.editTextEmail)

        if (user != null)
            textEmail.setText(user.email)

        btnChangeEmail.setOnClickListener {
            val email = textEmail.text.toString()

            /* Да, надо проверять на валидацию, но я не хочу */
            if (user != null && email != "") {
                user.updateEmail(email)
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