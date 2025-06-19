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
import com.example.patiobalmax.databinding.LoginScreenBinding

class LoginScreen : AppCompatActivity() {

    private lateinit var binding: LoginScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar el binding
        binding = LoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Acceder a los elementos desde el binding
        binding.loginButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (username == "admin" && password == "admin") {
                startActivity(Intent(this, MapScreen::class.java))
                finish()
            } else {
                // Mostrar mensaje de error
            }
        }
    }
}
