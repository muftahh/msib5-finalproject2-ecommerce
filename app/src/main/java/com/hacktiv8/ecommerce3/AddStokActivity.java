package com.hacktiv8.ecommerce3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AddStokActivity extends AppCompatActivity {
    private TextView idBarang, kategoriBarang, namaBarang, brand, jumlahBarang, hargaBarang;
    private ImageButton buttonSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stok);

        idBarang = findViewById(R.id.id_barang);
        kategoriBarang = findViewById(R.id.kategori_barang);
        namaBarang = findViewById(R.id.nama_barang);
        brand = findViewById(R.id.brand);
        jumlahBarang = findViewById(R.id.jumlah_barang);
        hargaBarang = findViewById(R.id.harga_barang);
        buttonSave = findViewById(R.id.save);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AdminActivity.class);
                startActivity(intent);
            }
        });

    }
}