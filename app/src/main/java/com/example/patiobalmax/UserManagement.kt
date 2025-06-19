package com.example.patiobalmax

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Toast
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.LinearLayout
import androidx.core.widget.NestedScrollView
import android.view.LayoutInflater
import android.view.View
import com.example.patiobalmax.databinding.UserManagementBinding

class UserManagement : AppCompatActivity() {

    private lateinit var binding: UserManagementBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = UserManagementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener { finish() }
    }
}
