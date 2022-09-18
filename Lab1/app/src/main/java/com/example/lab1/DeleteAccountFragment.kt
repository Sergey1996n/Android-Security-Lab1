package com.example.lab1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class DeleteAccountFragment: Fragment(R.layout.delete_account) {
    private lateinit var btnDeleteAccount: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnDeleteAccount = view.findViewById(R.id.btn_del_acc)

        btnDeleteAccount.setOnClickListener{
            val user = Firebase.auth.currentUser

            if (user != null) {
                user.delete()
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(context, "Мы будем скучать без тебя", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            Toast.makeText(context, "Я бы тебя удалил, но есть какие-то проблемы", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
            }
            else {
                Toast.makeText(context, "А некого удалять", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }
}