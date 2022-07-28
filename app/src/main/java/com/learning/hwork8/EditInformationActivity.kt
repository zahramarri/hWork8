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
    }

    private fun setAllTexts() {
        binding.edtIdentificationNumber.setText("کد ملی:   " + readFromSharedPref(binding.edtIdentificationNumber.id))
        binding.edtBirthPlace.setText("محل تولد:   " + readFromSharedPref(binding.edtBirthPlace.id))
        binding.edtAddress.setText("آدرس:   " + readFromSharedPref(binding.edtAddress.id))
        binding.edtPostalCode.setText("کد پستی:   " + readFromSharedPref(binding.edtPostalCode.id))
        binding.edtGender.setText("جنسیت:   " + readFromSharedPref(binding.edtGender.id))
    }

    private fun readFromSharedPref(viewId: Int): String? {
        val sharedPref = getPreferences(MODE_PRIVATE)
        return sharedPref.getString(viewId.toString(), "Empty")
    }
}