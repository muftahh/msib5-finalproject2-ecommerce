package com.hacktiv8.ecommerce3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class HomeUserActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton btnPakaian, btnElektronik, btnBuku, btnLainnya;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user);

        btnPakaian = findViewById(R.id.btnPakaian);
        btnElektronik = findViewById(R.id.btnElektronik);
        btnBuku = findViewById(R.id.btnBuku);
        btnLainnya = findViewById(R.id.btnLainnya);

        btnPakaian.setOnClickListener(this);
        btnElektronik.setOnClickListener(this);
        btnBuku.setOnClickListener(this);
        btnLainnya.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnPakaian) {
            Intent pakaianIntent = new Intent(this, KategoriProdukActivity.class);
            String screen = "Kategori Pakaian";
            pakaianIntent.putExtra("namaKategori", screen);
            startActivity(pakaianIntent);
        } else if (view.getId() == R.id.btnElektronik) {
            Intent elektronikIntent = new Intent(this, KategoriProdukActivity.class);
            String screen = "Kategori Elektronik";
            elektronikIntent.putExtra("namaKategori", screen);
            startActivity(elektronikIntent);
        } else if (view.getId() == R.id.btnBuku) {
            Intent bukuIntent = new Intent(this, ListProdukActivity.class);
            startActivity(bukuIntent);
        } else if (view.getId() == R.id.btnLainnya) {
            Intent lainnyaIntent = new Intent(this, ListProdukActivity.class);
            startActivity(lainnyaIntent);
        }
    }
}