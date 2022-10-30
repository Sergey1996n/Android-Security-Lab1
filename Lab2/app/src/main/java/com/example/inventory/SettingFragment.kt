package com.example.inventory

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.inventory.databinding.FragmentSettingBinding


class SettingFragment: Fragment() {
    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    private val PREFS_FILE = "Setting"

    private var settings: SharedPreferences? = null
    private var prefEditor: SharedPreferences.Editor? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        settings = requireContext().getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        binding.apply {
            defaultProviderName.setText(settings!!.getString("ProviderName",""))
            defaultProviderEmail.setText(settings!!.getString("ProviderEmail",""))
            defaultProviderPhone.setText(settings!!.getString("ProviderPhone",""))
            checkBoxDefault.isChecked = settings!!.getBoolean("CheckBoxDefault",false)
            checkBoxHide.isChecked = settings!!.getBoolean("CheckBoxHide",false)
            checkBoxForbid.isChecked = settings!!.getBoolean("CheckBoxForbid",false)

            saveAction.setOnClickListener {
                settings!!.edit().apply {
                    putString("ProviderName", defaultProviderName.text.toString())
                    putString("ProviderEmail", defaultProviderEmail.text.toString())
                    putString("ProviderPhone", defaultProviderPhone.text.toString())
                    putBoolean("CheckBoxDefault", checkBoxDefault.isChecked)
                    putBoolean("CheckBoxHide", checkBoxHide.isChecked)
                    putBoolean("CheckBoxForbid", checkBoxForbid.isChecked)
                }.apply()

                val action = SettingFragmentDirections.actionSettingFragmentToItemListFragment()
                findNavController().navigate(action)
            }
        }


    }
}