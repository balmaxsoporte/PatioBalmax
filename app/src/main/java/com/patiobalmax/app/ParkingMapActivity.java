package com.patiobalmax.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class ParkingMapActivity extends AppCompatActivity {

    private ImageView mapImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_map);

        mapImage = findViewById(R.id.mapImageView);

        mapImage.setOnClickListener(v -> {
            startActivity(new Intent(ParkingMapActivity.this, ParkingDetailsActivity.class));
        });
    }
}
