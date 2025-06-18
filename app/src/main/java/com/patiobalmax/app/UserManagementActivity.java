package com.patiobalmax.app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class UserManagementActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private CheckBox cbAdmin, cbEditSpots, cbEditPlates;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_management);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        cbAdmin = findViewById(R.id.cbAdmin);
        cbEditSpots = findViewById(R.id.cbEditSpots);
        cbEditPlates = findViewById(R.id.cbEditPlates);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {
            // Guardar lógica aquí
        });
    }
}
