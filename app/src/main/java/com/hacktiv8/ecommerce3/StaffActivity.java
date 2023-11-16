package com.hacktiv8.ecommerce3;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StaffActivity extends AppCompatActivity {
    private TextView id, namaLengkap, email, pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        id = findViewById(R.id.id);
        namaLengkap = findViewById(R.id.nama_lengkap);
        email = findViewById(R.id.email);
        pw = findViewById(R.id.pw);
    }
}