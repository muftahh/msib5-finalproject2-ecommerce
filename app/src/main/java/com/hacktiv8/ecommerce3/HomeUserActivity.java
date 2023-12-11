package com.hacktiv8.ecommerce3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class HomeUserActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user);

        ImageButton btnPakaian = findViewById(R.id.btnPakaian);
        ImageButton btnElektronik = findViewById(R.id.btnElektronik);
        ImageButton btnBuku = findViewById(R.id.btnBuku);
        ImageButton btnLainnya = findViewById(R.id.btnLainnya);
        ImageButton mlogoutBtn = findViewById(R.id.logoutBtn2);

        btnPakaian.setOnClickListener(this);
        btnElektronik.setOnClickListener(this);
        btnBuku.setOnClickListener(this);
        btnLainnya.setOnClickListener(this);

        mlogoutBtn.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(HomeUserActivity.this, WelcomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnPakaian) {
            Intent pakaianIntent = new Intent(this, KategoriProdukActivity.class);
            String screen = "Kategori Pakaian";
            pakaianIntent.putExtra("namaKategori", screen);
            startActivity(pakaianIntent);
        }

        else if (view.getId() == R.id.btnElektronik) {
            Intent elektronikIntent = new Intent(this, KategoriProdukActivity.class);
            String screen = "Kategori Elektronik";
            elektronikIntent.putExtra("namaKategori", screen);
            startActivity(elektronikIntent);
        }

        else if (view.getId() == R.id.btnBuku) {
            String childDataBase = "Buku";
            Intent bukuIntent = new Intent(this, ListProdukActivity.class);
            bukuIntent.putExtra("childDataBase", childDataBase);
            startActivity(bukuIntent);
        }

        else if (view.getId() == R.id.btnLainnya) {
            String childDataBase = "Lainnya";
            Intent lainnyaIntent = new Intent(this, ListProdukActivity.class);
            lainnyaIntent.putExtra("childDataBase", childDataBase);
            startActivity(lainnyaIntent);
        }
    }
}