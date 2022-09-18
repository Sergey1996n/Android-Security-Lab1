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

        val user = Firebase.auth.currentUser

        var url: Uri? = Uri.parse("")
        var name: String?
        if (user != null){
            name = user.displayName
            url = user.photoUrl
            if (name != null && url != null) {
                input.setText(name)
                //imageView.setImageURI(url)
            }
        }

        val getContent = registerForActivityResult(ActivityResultContracts.GetContent())  { uri: Uri? ->
            imageView.setImageURI(uri)
            url = uri
        }

        imageView.setOnClickListener {
            getContent.launch("image/*")
        }

        button.setOnClickListener {
            name = input.text.toString()
            /* url просит инициализации */
            if (name != "" && user != null && url != null) {
                val profileUpdates = userProfileChangeRequest {
                    displayName = name
                    photoUri = url
                }
                user.updateProfile(profileUpdates)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText( context, "У тебя получилось!!!", Toast.LENGTH_SHORT).show()
                        }
                        else{
                            Toast.makeText( context, "Произошла ерунда.", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
            else {
                Toast.makeText( context, "Произошла ерунда.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}