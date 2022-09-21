package com.example.lab1

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class PersonalDataFragment : Fragment(R.layout.personal_data) {
    private lateinit var navController: NavController

    private lateinit var auth: FirebaseAuth

    private lateinit var nameText: TextView
    private lateinit var image: ImageView
    private lateinit var emailText: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nameText = view.findViewById(R.id.textName)
        image = view.findViewById(R.id.imageView)
        emailText = view.findViewById(R.id.textEmail)

//        Log.wtf("AuthenticationFragment", Firebase.auth.currentUser?.zza().toString())
//        Log.wtf("AuthenticationFragment", Firebase.auth.currentUser?.zzb()?.providerId.toString())
//        Log.wtf("AuthenticationFragment", Firebase.auth.currentUser?.zze().toString())
//        Log.wtf("AuthenticationFragment", Firebase.auth.currentUser?.zzf().toString())
//        Log.wtf("AuthenticationFragment", Firebase.auth.currentUser?.zzy().toString())
        Log.wtf("AuthenticationFragment", Firebase.auth.firebaseAuthSettings.toString())

        val user = Firebase.auth.currentUser!!

        user?.let {
            nameText.text = user.displayName
            emailText.text = user.email
            /* Всё решилось другой библеотекой */
            Glide.with(this).load(user.photoUrl).into(image)
//            emailVerified.text = Firebase.auth.firebaseAuthSettings.
        }
    }
}