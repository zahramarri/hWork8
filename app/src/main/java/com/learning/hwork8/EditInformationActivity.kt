package com.learning.hwork8

import android.content.Intent
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
            it.visibility = View.GONE
            binding.btnRegister.visibility = View.VISIBLE
        }

        binding.btnRegister.setOnClickListener {
            writeInSharedPref()
            setAllEditTextsDisabled()
        }

        binding.btnEnterNewly.setOnClickListener {
            sharedPref.edit().clear().apply()
            startActivityRegistry()
        }
    }

    private fun startActivityRegistry() {
        val intent = Intent(this, RegistryActivity::class.java)
        startActivity(intent)
    }

    private fun writeInSharedPref() {
        val editor = sharedPref.edit()
        editor.putString("edtIdentificationNumber", binding.edtIdentificationNumber.text.toString())
        editor.putString("edtBirthPlace", binding.edtBirthPlace.text.toString())
        editor.putString("edtAddress", binding.edtAddress.text.toString())
        editor.putString("edtPostalCode", binding.edtPostalCode.text.toString())
        editor.putString("edtGender", binding.edtGender.text.toString())
        editor.apply()
    }

    private fun setAllEditTextsEnabled() {
        binding.edtIdentificationNumber.isEnabled = true
        binding.edtIdentificationNumber.isEnabled = true
        binding.edtBirthPlace.isEnabled = true
        binding.edtAddress.isEnabled = true
        binding.edtPostalCode.isEnabled = true
        binding.edtGender.isEnabled = true
    }

    private fun setAllEditTextsDisabled() {
        binding.edtIdentificationNumber.isEnabled = false
        binding.edtIdentificationNumber.isEnabled = false
        binding.edtBirthPlace.isEnabled = false
        binding.edtAddress.isEnabled = false
        binding.edtPostalCode.isEnabled = false
        binding.edtGender.isEnabled = false
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