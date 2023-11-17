package com.hacktiv8.ecommerce3;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class ListProdukActivity extends AppCompatActivity {
    private RecyclerView recyclerViewProduk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_produk);

        recyclerViewProduk = findViewById(R.id.recyclerViewProduk);
    }
}