package com.example.lab1

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase


class ChangePersonalDataFragment: Fragment(R.layout.change_personal_data) {

    private lateinit var imageView: ImageView
    private lateinit var input: EditText
    private lateinit var button: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageView = view.findViewById(R.id.imageView)
        input = view.findViewById(R.id.editTextPersonName)
        button = view.findViewById(R.id.btn_change)

        var name: String
        var urlString: String = ""

        val getContent = registerForActivityResult(ActivityResultContracts.GetContent())  { uri: Uri? ->
            imageView.setImageURI(uri)    // Handle the returned Uri
            urlString = uri.toString()
        }

        imageView.setOnClickListener {
            getContent.launch("image/*")
        }

        button.setOnClickListener {
            val user = Firebase.auth.currentUser
            name = input.text.toString()
            if (name != "" && urlString != "" && user != null) {

                val profileUpdates = userProfileChangeRequest {
                    displayName = name
                    photoUri = Uri.parse(urlString)
                }

                user!!.updateProfile(profileUpdates)
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