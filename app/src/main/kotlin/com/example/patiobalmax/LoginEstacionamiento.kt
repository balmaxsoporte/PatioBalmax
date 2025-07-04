package com.example.patiobalmax

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.patiobalmax.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginEstacionamiento : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_estacionamiento)

        val etUsuario = findViewById<EditText>(R.id.etUsuario)
        val etContrasena = findViewById<EditText>(R.id.etContrasena)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        val db by lazy { AppDatabase.getDatabase(this) }

        btnLogin.setOnClickListener {
            val usuario = etUsuario.text.toString()
            val contrasena = etContrasena.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                val user = db.usuarioDao().findByNombre(usuario)
                runOnUiThread {
                    if (user != null && user.contrasena == contrasena) {
                        startActivity(Intent(this@LoginEstacionamiento, MapaEstacionamiento::class.java))
                    } else {
                        Toast.makeText(this@LoginEstacionamiento, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
