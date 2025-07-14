package com.example.patiobalmax.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.patiobalmax.database.AppDatabase;
import com.example.patiobalmax.database.PuestoEntity;

public class EditarPatioEstacionamiento extends AppCompatActivity {

    private EditText etPatente1, etPatente2;
    private Spinner spTipoVehiculo1, spTipoVehiculo2;
    private Button btnGuardar;
    private PuestoEntity puesto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_patio_estacionamiento);

        etPatente1 = findViewById(R.id.etPatente1);
        etPatente2 = findViewById(R.id.etPatente2);
        spTipoVehiculo1 = findViewById(R.id.spTipoVehiculo1);
        spTipoVehiculo2 = findViewById(R.id.spTipoVehiculo2);
        btnGuardar = findViewById(R.id.btnGuardar);

        // Recuperar el puesto seleccionado desde la intención
        if (getIntent().hasExtra("puesto")) {
            puesto = getIntent().getParcelableExtra("puesto");

            // Llenar campos con los datos actuales del puesto
            etPatente1.setText(puesto.getPatente1());
            etPatente2.setText(puesto.getPatente2());
            // Aquí iría lógica para setear los spinners según el tipo de vehículo actual
        }

        btnGuardar.setOnClickListener(v -> guardarCambios());
    }

    private void guardarCambios() {
        String patente1 = etPatente1.getText().toString();
        String patente2 = etPatente2.getText().toString();

        if (patente1.isEmpty()) {
            Toast.makeText(this, "La patente 1 es obligatoria.", Toast.LENGTH_SHORT).show();
            return;
        }

        puesto.setPatente1(patente1);
        puesto.setPatente2(patente2);
        // Actualizar los tipos de vehículo según el spinner

        AppDatabase db = AppDatabase.getDatabase(this);
        db.puestoDao().update(puesto);

        Toast.makeText(this, "Cambios guardados correctamente.", Toast.LENGTH_SHORT).show();
        finish();
    }
}
