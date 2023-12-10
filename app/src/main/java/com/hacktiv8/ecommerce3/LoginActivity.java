package com.hacktiv8.ecommerce3;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private String receivedScreen, email, password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // ...
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        Intent intent = getIntent();
        if (intent != null) {
            receivedScreen = intent.getStringExtra("Activity");
        }
        TextView descLogin = findViewById(R.id.desc_login);
        EditText mEmail = findViewById(R.id.email);
        EditText mPassword = findViewById(R.id.pw);
        ImageButton loginButton = findViewById(R.id.login);

        descLogin.setText(receivedScreen);

            loginButton.setOnClickListener(v -> {
                email = mEmail.getText().toString();
                password = mPassword.getText().toString();

                if ("Login Sebagai Admin".equals(receivedScreen)) {
                    login(email, password, "admins");
                } else if ("Login Sebagai Staff".equals(receivedScreen)) {
                    login(email, password, "staffs");
                } else {
                    login(email, password, "members");
                }
            });

        }

    private void login (String email, String password, String role){
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Email tidak boleh kosong!", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Password tidak boleh kosong", Toast.LENGTH_LONG).show();
            return;
        }
        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Isikan Password minimal 6 karakter", Toast.LENGTH_LONG).show();
            return;
        }

        Toast.makeText(getApplicationContext(), "Sedang Diproses", Toast.LENGTH_LONG).show();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Login berhasil

                        // Dapatkan UID pengguna
                        String userId = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
                        System.out.println(userId);

                        // Baca data pengguna dari Realtime Database
                        DatabaseReference userRef = FirebaseDatabase.getInstance("https://hacktiv8-ecommerce3-default-rtdb.asia-southeast1.firebasedatabase.app/")
                                .getReference(role).child(userId);

                        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    // Pengguna ditemukan, tampilkan pesan selamat datang sesuai peran
                                    if ("admins".equals(role)) {
                                        Toast.makeText(getApplicationContext(), "Welcome, Admin!", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                                        startActivity(intent);
                                    } else if ("staffs".equals(role)) {
                                        Toast.makeText(getApplicationContext(), "Welcome, Staff!", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(), StaffActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Welcome, Members!", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(), HomeUserActivity.class);
                                        startActivity(intent);
                                    }
                                } else {
                                    // Jika tidak ditemukan, mungkin ada kesalahan
                                    Toast.makeText(getApplicationContext(), "User data not found", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }

                        });

                    } else {
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            // Format email tidak valid
                            Toast.makeText(getApplicationContext(), "Email atau Password Salah, Silahkan periksa kembali", Toast.LENGTH_LONG).show();
                        } else {
                            // Kesalahan lain
                            Toast.makeText(getApplicationContext(), "Login Gagal! Cek koneksi lalu coba lagi", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}