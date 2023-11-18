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

public class RegisterUsersActivity extends AppCompatActivity {
    private EditText inputNamaMember;
    private EditText inputEmailMember;
    private EditText inputPasswordMember1;
    private EditText inputPasswordMember2;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_users);

        // ...
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        inputNamaMember = findViewById(R.id.inputNamaMember);
        inputEmailMember = findViewById(R.id.inputEmailMember);
        inputPasswordMember1 = findViewById(R.id.inputPasswordMember1);
        inputPasswordMember2 = findViewById(R.id.inputPasswordMember2);

        ImageButton bntSignUp = findViewById(R.id.bntSignUp);
        bntSignUp.setOnClickListener(v -> registerNewUser());
    }

    private void registerNewUser() {
        String namaLengkap, email, password,rePassword;
        email = inputEmailMember.getText().toString();
        password = inputPasswordMember1.getText().toString();
        namaLengkap = inputNamaMember.getText().toString();
        rePassword = inputPasswordMember2.getText().toString();


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
            inputPasswordMember1.setText("");
            inputPasswordMember2.setText("");
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
                        DatabaseReference userRef = mDatabase.getReference("members").child(userId);

                        userRef.child("name").setValue(namaLengkap); // Gantilah dengan data yang sesuai
                        userRef.child("email").setValue(email);
                        userRef.child("jobs").setValue("user");

                        // Tambahkan tindakan selanjutnya jika diperlukan
                        Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                        startActivity(intent);
                    } else {
                        // Pendaftaran gagal, cek apakah kesalahan disebabkan oleh email sudah terdaftar
                        if (task1.getException() instanceof FirebaseAuthUserCollisionException) {
                            // Email sudah terdaftar
                            Toast.makeText(getApplicationContext(), "Email Telah terdaftar, silahkan lanjut login", Toast.LENGTH_LONG).show();
                        } else {
                            // Kesalahan lain, tampilkan pesan kegagalan
                            Toast.makeText(getApplicationContext(), "Registrasi Gagal! Silahkan coba lagi nanti", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }


}