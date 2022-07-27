package com.learning.hwork8

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.learning.hwork8.databinding.ActivityRegistryBinding


class RegistryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRedAsterisks()
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
}