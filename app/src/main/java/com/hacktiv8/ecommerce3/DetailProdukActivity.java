package com.hacktiv8.ecommerce3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

public class DetailProdukActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);

        String mDetailNamaProduk = getIntent().getStringExtra("mDetailNamaProduk");
        String mDetailMerekProduk = getIntent().getStringExtra("mDetailMerekProduk");
        String mDetailStokProduk = getIntent().getStringExtra("mDetailStokProduk");
        String mDetailHargaProduk = getIntent().getStringExtra("mDetailHargaProduk");

        TextView detailNamaProduk = findViewById(R.id.detailNamaProduk);
        TextView detailMerekProduk = findViewById(R.id.detailMerekProduk);
        TextView detailStokProduk = findViewById(R.id.detailStokProduk);
        TextView detailHargaProduk = findViewById(R.id.detailHargaProduk);

        detailNamaProduk.setText("Nama : " + mDetailNamaProduk);
        detailMerekProduk.setText("Brand : " +mDetailMerekProduk);
        detailStokProduk.setText("Jumlah : " +mDetailStokProduk);
        detailHargaProduk.setText("Harga : " +mDetailHargaProduk);

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Menangani tombol "Back"
                // Kembali ke ListProdukActivity dan kirim informasi kategori
                Intent intent = new Intent(DetailProdukActivity.this, ListProdukActivity.class);
                intent.putExtra("childDataBase", getIntent().getStringExtra("mKategori"));
                startActivity(intent);
                finish();
            }
        });
    }

}