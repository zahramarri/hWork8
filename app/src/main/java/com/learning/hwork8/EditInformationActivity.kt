package com.learning.hwork8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.learning.hwork8.databinding.ActivityEditInformationBinding

class EditInformationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditInformationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAllTexts()

        binding.btnEditInformation.setOnClickListener{
            setAllEditTextsEnabled()
        }
    }

    private fun setAllEditTextsEnabled() {
        binding.edtIdentificationNumber.isEnabled = true
        binding.edtIdentificationNumber.isEnabled = true
        binding.edtBirthPlace.isEnabled = true
        binding.edtAddress.isEnabled = true
        binding.edtPostalCode.isEnabled = true
        binding.edtGender.isEnabled = true
    }

    private fun setAllTexts() {
        binding.edtIdentificationNumber.setText("کد ملی:   " + readFromSharedPref("edtIdentificationNumber"))
        binding.edtBirthPlace.setText("محل تولد:   " + readFromSharedPref("edtBirthPlace"))
        binding.edtAddress.setText("آدرس:   " + readFromSharedPref("edtAddress"))
        binding.edtPostalCode.setText("کد پستی:   " + readFromSharedPref("edtPostalCode"))
        binding.edtGender.setText("جنسیت:   " + readFromSharedPref("edtGender"))
    }

    private fun readFromSharedPref(key: String): String? {
        return sharedPref.getString(key, "Empty")
    }
}