package com.patiobalmax.app;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class AddEditParkingSpotActivity extends AppCompatActivity {

    private Spinner spinnerVehicleType, spinnerTrailerType;
    private EditText etPlate, etTrailerPlate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_parking_spot);

        spinnerVehicleType = findViewById(R.id.spinnerVehicleType);
        spinnerTrailerType = findViewById(R.id.spinnerTrailerType);
        etPlate = findViewById(R.id.etPlate);
        etTrailerPlate = findViewById(R.id.etTrailerPlate);
    }
}
