package com.hacktiv8.ecommerce3;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddStokActivity extends AppCompatActivity {
    private EditText namaBarang, brand, jumlahBarang, hargaBarang;
    private Spinner kategoriBarang;
    private ImageButton buttonSave;
    private TextView errorTextAddStok;
    private DatabaseReference mDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stok);

        mDataBase = FirebaseDatabase.getInstance("https://hacktiv8-ecommerce3-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("Barang");

        kategoriBarang = findViewById(R.id.kategori_barang);

        namaBarang = findViewById(R.id.nama_barang);
        brand = findViewById(R.id.brand);
        jumlahBarang = findViewById(R.id.jumlah_barang);
        hargaBarang = findViewById(R.id.harga_barang);

        errorTextAddStok = findViewById(R.id.errorTextAddStok);
        buttonSave = findViewById(R.id.save);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStockProduk();
            }
        });
        buttonSave.setOnClickListener(v -> addStockProduk());
    }

    private void addStockProduk() {
        String mKategoriBarang, mNamaBarang, mBrand, mJumlahBarang, mHargaBarang;

        mKategoriBarang = kategoriBarang.getSelectedItem().toString();
        mNamaBarang = namaBarang.getText().toString();
        mBrand = brand.getText().toString();
        mJumlahBarang = jumlahBarang.getText().toString();
        mHargaBarang = hargaBarang.getText().toString();

        //Validasi data
        if (mKategoriBarang.equals("Pilih") || TextUtils.isEmpty(mNamaBarang) || TextUtils.isEmpty(mBrand) || TextUtils.isEmpty(mJumlahBarang) || TextUtils.isEmpty(mHargaBarang)) {
            errorTextAddStok.setText("Semua data harus terisi");
            return;
        }

        Barang barang = new Barang(mKategoriBarang, mNamaBarang, mBrand, mJumlahBarang, mHargaBarang);
        mDataBase.child(mKategoriBarang).push().setValue(barang).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(AddStokActivity.this, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddStokActivity.this, AdminActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(AddStokActivity.this, "Gagal menambahkan data", Toast.LENGTH_SHORT).show();
            }
        });


    }
}