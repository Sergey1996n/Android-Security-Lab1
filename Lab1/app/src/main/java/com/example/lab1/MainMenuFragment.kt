package com.example.lab1

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI


class MainMenuFragment : Fragment(R.layout.main_menu) {
    private lateinit var navController: NavController
    private lateinit var btnAuth: Button
    private lateinit var btnData: Button
    private lateinit var btnSignOutFragment: Button
    private lateinit var btnAvatarName: Button
    private lateinit var btnChangeEmail: Button
    private lateinit var btnZone: Button
    private lateinit var btnDelete: Button

    private lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        navController = Navigation.findNavController(view);

        /** Инициализируем наши вьюшки */
        btnData = view.findViewById(R.id.btn_data)
        btnSignOutFragment = view.findViewById(R.id.btn_sign_out_fragment)
        btnAvatarName = view.findViewById(R.id.btn_avatar_name)
        btnChangeEmail = view.findViewById(R.id.btn_email)
        btnZone = view.findViewById(R.id.btn_zone)
        btnDelete = view.findViewById(R.id.btn_delete)

        // Initialize Firebase Auth
        auth = Firebase.auth

        btnData.setOnClickListener {
            navController.navigate(R.id.action_mainMenu_to_personalData)
        }
        btnSignOutFragment.setOnClickListener {
            navController.navigate(R.id.action_mainMenu_to_signOutFragment)
        }
        btnAvatarName.setOnClickListener {
            navController.navigate(R.id.action_mainMenu_to_changePersonalDataFragment)
        }
        btnChangeEmail.setOnClickListener {
            navController.navigate(R.id.action_mainMenu_to_changeEmail)
        }
        btnZone.setOnClickListener {
            val user = Firebase.auth.currentUser
            if (user != null)
                navController.navigate(R.id.action_mainMenu_to_zone)
            else
                Toast.makeText( context, "Ты не пройдёшь!!!", Toast.LENGTH_SHORT).show()
        }
        btnDelete.setOnClickListener {
            navController.navigate(R.id.action_mainMenu_to_deleteAccountFragment)
        }
    }
}