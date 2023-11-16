package com.hacktiv8.ecommerce3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterStaffActivity extends AppCompatActivity {
    private EditText idStaff, namaLengkap, email, pw, konfirmasiPw;
    ImageButton buttonSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_staff);

        idStaff = findViewById(R.id.id_staff);
        namaLengkap = findViewById(R.id.nama_lengkap);
        email = findViewById(R.id.email);
        pw = findViewById(R.id.pw);
        konfirmasiPw = findViewById(R.id.konfirmasi_pw);
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