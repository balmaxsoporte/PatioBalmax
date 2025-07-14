package com.example.patiobalmax.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.patiobalmax.adapter.PatioAdapter;
import com.example.patiobalmax.database.AppDatabase;
import com.example.patiobalmax.database.PatioEntity;

import java.util.List;

public class MapaEstacionamiento extends AppCompatActivity {

    private ImageView ivMapa;
    private TextView tvTitulo;
    private RecyclerView rvPatios;
    private PatioAdapter patioAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_estacionamiento);

        ivMapa = findViewById(R.id.ivMapa);
        tvTitulo = findViewById(R.id.tvTitulo);
        rvPatios = findViewById(R.id.rvPatios);

        tvTitulo.setText(getResources().getString(R.string.lbl_bienvenida));

        AppDatabase db = AppDatabase.getDatabase(this);
        List<PatioEntity> patios = db.patioDao().getAll();

        patioAdapter = new PatioAdapter(patios, this::navegarAPatio);
        rvPatios.setLayoutManager(new LinearLayoutManager(this));
        rvPatios.setAdapter(patioAdapter);
    }

    private void navegarAPatio(PatioEntity patio) {
        Intent intent = new Intent(MapaEstacionamiento.this, PatioEstacionamiento.class);
        intent.putExtra("patio", patio);
        startActivity(intent);
    }
}
