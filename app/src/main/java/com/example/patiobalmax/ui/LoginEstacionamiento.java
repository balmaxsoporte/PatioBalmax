package com.example.patiobalmax.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.patiobalmax.database.AppDatabase;
import com.example.patiobalmax.database.UsuarioEntity;

public class LoginEstacionamiento extends AppCompatActivity {

    private EditText etUsuario, etContrasena;
    private TextView tvMensaje;
    private Button btnIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_estacionamiento);

        etUsuario = findViewById(R.id.etUsuario);
        etContrasena = findViewById(R.id.etContrasena);
        tvMensaje = findViewById(R.id.tvMensaje);
        btnIngresar = findViewById(R.id.btnIngresar);

        btnIngresar.setOnClickListener(v -> {
            String usuario = etUsuario.getText().toString();
            String contrasena = etContrasena.getText().toString();

            if (validarCredenciales(usuario, contrasena)) {
                AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
                UsuarioEntity user = db.usuarioDao().getUsuarioByNombre(usuario);
                Intent intent = new Intent(LoginEstacionamiento.this, MapaEstacionamiento.class);
                intent.putExtra("usuario", user);
                startActivity(intent);
                finish();
            } else {
                tvMensaje.setText(getResources().getString(R.string.lbl_usuario_invalido));
                tvMensaje.setVisibility(TextView.VISIBLE);
            }
        });
    }

    private boolean validarCredenciales(String usuario, String contrasena) {
        // Aquí puedes conectar a la base de datos local para verificar las credenciales
        return "admin".equals(usuario) && "admin".equals(contrasena)
                || "user1".equals(usuario) && "12345".equals(contrasena)
                || "user2".equals(usuario) && "12345".equals(contrasena);
    }
}
