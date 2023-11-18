package com.hacktiv8.ecommerce3;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class RegisterStaffActivity extends AppCompatActivity {
    private EditText mNamaLengkap, mEmail, mPassword, mKonfirmasiPassword;
    private FirebaseAuth mAuth;
    ImageButton buttonSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_staff);

        // ...
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        mNamaLengkap = findViewById(R.id.nama_lengkap);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.pw);
        mKonfirmasiPassword = findViewById(R.id.konfirmasi_pw);
        buttonSave = findViewById(R.id.save);

        buttonSave.setOnClickListener(v -> registerNewStaff());

    }


    private void registerNewStaff() {
        String namaLengkap, email, password,rePassword;
        email = mEmail.getText().toString();
        password = mPassword.getText().toString();
        namaLengkap = mNamaLengkap.getText().toString();
        rePassword = mKonfirmasiPassword.getText().toString();


        if (TextUtils.isEmpty(namaLengkap)) {
            Toast.makeText(getApplicationContext(), "Nama tidak boleh kosong!", Toast.LENGTH_LONG).show();
            return;
        }
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
        if (!password.equals(rePassword)) {
            Toast.makeText(getApplicationContext(), "Password tidak sama, silahkan coba lagi", Toast.LENGTH_LONG).show();
            mPassword.setText("");
            mKonfirmasiPassword.setText("");
            return;
        }

        // Pengecekan apakah email sudah terdaftar sebelumnya
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task1 -> {
                    if (task1.isSuccessful()) {
                        // Pendaftaran berhasil
                        Toast.makeText(getApplicationContext(), "Akun Berhasil Didaftarkan!", Toast.LENGTH_LONG).show();

                        // Pendaftaran berhasil, dapatkan ID pengguna
                        String userId = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();

                        // Simpan data pengguna ke Realtime Database
                        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance("https://hacktiv8-ecommerce3-default-rtdb.asia-southeast1.firebasedatabase.app/");
                        DatabaseReference userRef = mDatabase.getReference("staffs").child(userId);

                        userRef.child("name").setValue(namaLengkap); // Gantilah dengan data yang sesuai
                        userRef.child("email").setValue(email);
                        userRef.child("jobs").setValue("staff");

                        // Tambahkan tindakan selanjutnya jika diperlukan
                        Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                        startActivity(intent);
                    } else {
                        // Pendaftaran gagal, cek apakah kesalahan disebabkan oleh email sudah terdaftar
                        if (task1.getException() instanceof FirebaseAuthUserCollisionException) {
                            // Email sudah terdaftar
                            Toast.makeText(getApplicationContext(), "Email is already registered", Toast.LENGTH_LONG).show();
                        } else {
                            // Kesalahan lain, tampilkan pesan kegagalan
                            Toast.makeText(getApplicationContext(), "Registration failed! Please try again later", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }


}