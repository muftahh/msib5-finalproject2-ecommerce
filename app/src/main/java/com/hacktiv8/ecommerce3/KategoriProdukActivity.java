package com.hacktiv8.ecommerce3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class KategoriProdukActivity extends AppCompatActivity {
    private TextView namaKategori;
    private String receivedScreen;
    private ImageButton btnKategoriProduk1, btnKategoriProduk2;
    private TextView kategori1, kategori2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori_produk);
        Intent intent = getIntent();
        if (intent != null) {
            receivedScreen = intent.getStringExtra("namaKategori");
        }

        namaKategori = findViewById(R.id.namaKategori);
        btnKategoriProduk1 = findViewById(R.id.btnKategoriProduk1);
        btnKategoriProduk2 = findViewById(R.id.btnKategoriProduk2);
        kategori1 = findViewById(R.id.kategori1);
        kategori2 = findViewById(R.id.kategori2);

        namaKategori.setText(receivedScreen);
        if ("Kategori Pakaian".equals(receivedScreen)){
            kategori1.setText("Pakaian Laki-Laki");
            kategori2.setText("Pakaian Perempuan");
        } else if ("Kategori Elektronik".equals(receivedScreen)) {
            kategori1.setText("Computer");
            kategori2.setText("Smartphone");
        }

    }
}