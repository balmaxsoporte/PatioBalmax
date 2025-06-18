package com.balmax.patiobalmax

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.balmax.patiobalmax.databinding.UserManagementBinding

class UserManagement : AppCompatActivity() {

    private lateinit var binding: UserManagementBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UserManagementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addButton.setOnClickListener {
            val username = binding.newUsername.text.toString()
            val password = binding.newPassword.text.toString()
            val editUsers = binding.editUsersCheck.isChecked
            val editLots = binding.editLotsCheck.isChecked
            val editPlates = binding.editPlatesCheck.isChecked

            // Aquí insertar usuario a base de datos o mostrar mensaje
        }

        binding.backButton.setOnClickListener { finish() }
    }
}
