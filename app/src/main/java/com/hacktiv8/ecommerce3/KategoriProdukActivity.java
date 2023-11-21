package com.hacktiv8.ecommerce3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class KategoriProdukActivity extends AppCompatActivity implements View.OnClickListener {
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

        //mengubah tampilan berdasarkan pilihan yang dipilih
        namaKategori.setText(receivedScreen);
        if ("Kategori Pakaian".equals(receivedScreen)){
            kategori1.setText("Pakaian Laki-Laki");
            kategori2.setText("Pakaian Wanita");
        }
        else if ("Kategori Elektronik".equals(receivedScreen)) {
            kategori1.setText("Computer");
            kategori2.setText("Smartphone");
        }

        btnKategoriProduk1.setOnClickListener(this);
        btnKategoriProduk2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if ("Kategori Pakaian".equals(receivedScreen)){
            if (view.getId() == R.id.btnKategoriProduk1) {
                //ketika pilihan pakaian pria
                String childDataBase = "Pakaian_Pria";
                Intent intentPakaianPria = new Intent(this, ListProdukActivity.class);
                intentPakaianPria.putExtra("childDataBase", childDataBase);
                startActivity(intentPakaianPria);
            }

            else if (view.getId() == R.id.btnKategoriProduk2) {
                //ketika pilihan pakaian wanita
                String childDataBase = "Pakaian_Wanita";
                Intent intentPakaianWanita = new Intent(this, ListProdukActivity.class);
                intentPakaianWanita.putExtra("childDataBase", childDataBase);
                startActivity(intentPakaianWanita);
            }
        }
        else if ("Kategori Elektronik".equals(receivedScreen)){
            if (view.getId() == R.id.btnKategoriProduk1) {
                //ketika pilihan Komputer
                String childDataBase = "Koputer";
                Intent intentComputer = new Intent(this, ListProdukActivity.class);
                intentComputer.putExtra("childDataBase", childDataBase);
                startActivity(intentComputer);
            }

            else if (view.getId() == R.id.btnKategoriProduk2) {
                //ketika pilihan Smartphone
                String childDataBase = "Handphone";
                Intent intentSmartphone = new Intent(this, ListProdukActivity.class);
                intentSmartphone.putExtra("childDataBase", childDataBase);
                startActivity(intentSmartphone);
            }
        }
    }
}