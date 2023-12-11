package com.hacktiv8.ecommerce3;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ListProdukActivity extends AppCompatActivity implements RecyclerViewInterface {
    private RecyclerView recyclerViewProduk;
    private String mChildDataBase;
    private ProdukAdapter produkAdapter;
    private FirebaseRecyclerOptions<Barang> options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_produk);

        Intent intent = getIntent();
        if (intent != null) {
            mChildDataBase = intent.getStringExtra("childDataBase");
        }

        recyclerViewProduk = findViewById(R.id.recyclerViewProduk);
        recyclerViewProduk.setLayoutManager(new LinearLayoutManager(this));

        options = new FirebaseRecyclerOptions.Builder<Barang>()
                .setQuery(FirebaseDatabase.getInstance("https://hacktiv8-ecommerce3-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("Barang").child(mChildDataBase), Barang.class)
                .build();

        produkAdapter = new ProdukAdapter(options, this);
        recyclerViewProduk.setAdapter(produkAdapter);

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(ListProdukActivity.this, HomeUserActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        if (produkAdapter != null) {
            produkAdapter.startListening();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (produkAdapter != null) {
            produkAdapter.stopListening();
        }
    }

    @Override
    public void onDetailClick(int position) {
        // Mengirim data ke DetailProdukActivity dan kategori
        Intent intent = new Intent(ListProdukActivity.this, DetailProdukActivity.class);
        intent.putExtra("mDetailNamaProduk", options.getSnapshots().get(position).mNamaBarang);
        intent.putExtra("mDetailMerekProduk", options.getSnapshots().get(position).mBrand);
        intent.putExtra("mDetailStokProduk", options.getSnapshots().get(position).mJumlahBarang);
        intent.putExtra("mDetailHargaProduk", options.getSnapshots().get(position).mHargaBarang);
        intent.putExtra("mKategori", mChildDataBase); // Mengirim informasi kategori
        startActivity(intent);
    }
}
