package com.learning.hwork8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.learning.hwork8.databinding.ActivityRegistryBinding

class RegistryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistryBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}