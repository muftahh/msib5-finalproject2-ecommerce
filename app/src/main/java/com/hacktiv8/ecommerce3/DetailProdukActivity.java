package com.hacktiv8.ecommerce3;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailProdukActivity extends AppCompatActivity {
    private TextView detailNamaProduk, detailMerekProduk, detailStokProduk, detailHargaProduk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);

        String mDetailNamaProduk = getIntent().getStringExtra("mDetailNamaProduk");
        String mDetailMerekProduk = getIntent().getStringExtra("mDetailMerekProduk");
        String mDetailStokProduk = getIntent().getStringExtra("mDetailStokProduk");
        String mDetailHargaProduk = getIntent().getStringExtra("mDetailHargaProduk");

        detailNamaProduk = findViewById(R.id.detailNamaProduk);
        detailMerekProduk = findViewById(R.id.detailMerekProduk);
        detailStokProduk = findViewById(R.id.detailStokProduk);
        detailHargaProduk = findViewById(R.id.detailHargaProduk);

        detailNamaProduk.setText("Nama : " +mDetailNamaProduk);
        detailMerekProduk.setText("Brand : " +mDetailMerekProduk);
        detailStokProduk.setText("Jumlah : " +mDetailStokProduk);
        detailHargaProduk.setText("Harga : " +mDetailHargaProduk);
    }
}