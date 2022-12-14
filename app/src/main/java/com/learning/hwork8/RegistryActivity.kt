package com.learning.hwork8

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.learning.hwork8.databinding.ActivityRegistryBinding

lateinit var sharedPref: SharedPreferences

class RegistryActivity : AppCompatActivity() {
    private val digits = listOf('1', '2', '3', '4', '5', '6', '7', '8', '9', '0')
    private lateinit var binding: ActivityRegistryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPref = getPreferences(MODE_PRIVATE)

        if (checkInformationAvailability()) {
            startActivityEditInformation()
        }

        setRedAsterisks()

        binding.btRegister.setOnClickListener {
            controlBlankFields()
            controlIdentificationNumberField()
            controlPostalCodeField()
            if (noFieldBlank()) {
                writeInSharedPref()
                startActivityEditInformation()
            }
        }
    }

    private fun noFieldBlank(): Boolean {
        return (binding.edtIdentificationNumber.error == null &&
                binding.edtFullName.error == null &&
                binding.edtBirthPlace.error == null &&
                binding.edtPostalCode.error == null &&
                binding.edtAddress.error == null &&
                (binding.rbFemale.isChecked || binding.rbMale.isChecked))
    }

    private fun checkInformationAvailability(): Boolean {
        return (sharedPref.getString("edtIdentificationNumber", "Empty") != "Empty" &&
                sharedPref.getString("edtBirthPlace", "Empty") != "Empty" &&
                sharedPref.getString("edtAddress", "Empty") != "Empty" &&
                sharedPref.getString("edtPostalCode", "Empty") != "Empty" &&
                sharedPref.getString("edtGender", "Empty") != "Empty")
    }

    private fun startActivityEditInformation() {
        val intent = Intent(this, EditInformationActivity::class.java)
        startActivity(intent)
    }

    private fun controlPostalCodeField() {
        for (char in binding.edtPostalCode.text) {
            if (char !in digits) {
                binding.edtPostalCode.error = getString(R.string.errPostalCodeField)
            }
        }
    }

    private fun controlIdentificationNumberField() {
        if (binding.edtIdentificationNumber.text.length != 10) {
            binding.edtIdentificationNumber.error =
                getString(R.string.err10digitsIdentificationNumberField)
        } else {
            for (char in binding.edtIdentificationNumber.text) {
                if (char !in digits) {
                    binding.edtIdentificationNumber.error =
                        getString(R.string.errNumericIdentificationNumberField)
                }
            }
        }
    }

    private fun controlBlankFields() {
        if (binding.edtFullName.text.isNullOrBlank()) {
            binding.edtFullName.error = getString(R.string.errEmptyFields)
        }
        if (binding.edtIdentificationNumber.text.isNullOrBlank()) {
            binding.edtIdentificationNumber.error = getString(R.string.errEmptyFields)
        }
        if (binding.edtBirthPlace.text.isNullOrBlank()) {
            binding.edtBirthPlace.error = getString(R.string.errEmptyFields)
        }
        if (binding.edtAddress.text.isNullOrBlank()) {
            binding.edtAddress.error = getString(R.string.errEmptyFields)
        }
        if (binding.edtPostalCode.text.isNullOrBlank()) {
            binding.edtPostalCode.error = getString(R.string.errEmptyFields)
        }
        if (!binding.rbFemale.isChecked && !binding.rbMale.isChecked) {
            binding.tvGender.error = getString(R.string.errGenderField)
        }
    }

    private fun setRedAsterisks() {
        addRedAsterisk(binding.edtFullName)
        addRedAsterisk(binding.edtIdentificationNumber)
        addRedAsterisk(binding.edtBirthPlace)
        addRedAsterisk(binding.edtAddress)
        addRedAsterisk(binding.edtPostalCode)
    }

    private fun addRedAsterisk(editText: EditText) {
        val simple = editText.hint as String
        val colored = " *"
        val builder = SpannableStringBuilder()

        builder.append(simple)
        val start = builder.length
        builder.append(colored)
        val end = builder.length

        builder.setSpan(
            ForegroundColorSpan(Color.RED), start, end,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        editText.hint = builder
    }

    private fun writeInSharedPref() {
        val editor = sharedPref.edit()
        editor.putString("edtIdentificationNumber", binding.edtIdentificationNumber.text.toString())
        editor.putString("edtBirthPlace", binding.edtBirthPlace.text.toString())
        editor.putString("edtAddress", binding.edtAddress.text.toString())
        editor.putString("edtPostalCode", binding.edtPostalCode.text.toString())
        val gender: String = if (binding.rbFemale.isChecked) {
            getString(R.string.genderFemale)
        } else {
            getString(R.string.genderMale)
        }
        editor.putString("edtGender", gender)
        editor.apply()
    }
}