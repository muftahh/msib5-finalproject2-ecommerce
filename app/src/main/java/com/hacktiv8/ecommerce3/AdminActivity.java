package com.hacktiv8.ecommerce3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        ImageButton addStaff = findViewById(R.id.add_staff);
        ImageButton addStok = findViewById(R.id.add_stok);
        ImageButton logout = findViewById(R.id.logout);

        addStaff.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), RegisterStaffActivity.class);
            startActivity(intent);
        });

        addStok.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), AddStokActivity.class);
            startActivity(intent);
        });

        logout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut(); // ini adalah baris untuk logout

            Intent intent = new Intent(v.getContext(), WelcomeActivity.class);
            startActivity(intent);
        });

    }

}