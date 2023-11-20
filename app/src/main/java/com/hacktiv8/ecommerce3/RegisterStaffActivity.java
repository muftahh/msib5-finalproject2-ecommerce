package com.hacktiv8.ecommerce3;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class RegisterStaffActivity extends AppCompatActivity {
    private EditText mNamaLengkap, mEmail, mPassword, mKonfirmasiPassword;
    private TextView errorNamaStaff, errorEmailStaff, errorPasswordStaff, errorKonfirmasiPasswordStaff;
    private FirebaseAuth mAuth;
    ImageButton buttonSave;
    private ProgressBar progressBarRegStaff;
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

        errorNamaStaff = findViewById(R.id.errorNama_lengkap);
        errorEmailStaff = findViewById(R.id.errorEmail);
        errorPasswordStaff = findViewById(R.id.errorPw);
        errorKonfirmasiPasswordStaff = findViewById(R.id.errorKonfirmasi_pw);

        progressBarRegStaff = findViewById(R.id.progressBarRegStaff);
        buttonSave = findViewById(R.id.save);

        buttonSave.setOnClickListener(v -> registerNewStaff());

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                updateProgressBarRegStaff();
            }
        };
        mNamaLengkap.addTextChangedListener(textWatcher);
        mEmail.addTextChangedListener(textWatcher);
        mPassword.addTextChangedListener(textWatcher);
        mKonfirmasiPassword.addTextChangedListener(textWatcher);
    }

    private void updateProgressBarRegStaff() {
        int filledEditTextCount = 0;
        if (!TextUtils.isEmpty(mNamaLengkap.getText())) {
            filledEditTextCount++;
        }
        if (!TextUtils.isEmpty(mEmail.getText())) {
            filledEditTextCount++;
        }
        if (!TextUtils.isEmpty(mPassword.getText())) {
            filledEditTextCount++;
        }
        if (!TextUtils.isEmpty(mKonfirmasiPassword.getText())) {
            filledEditTextCount++;
        }
        int progress = filledEditTextCount * 25;
        progressBarRegStaff.setProgress(progress);
    }


    private void registerNewStaff() {
        String namaLengkap, email, password,rePassword;
        email = mEmail.getText().toString();
        password = mPassword.getText().toString();
        namaLengkap = mNamaLengkap.getText().toString();
        rePassword = mKonfirmasiPassword.getText().toString();


        // Validasi Nama
        if (TextUtils.isEmpty(namaLengkap)) {
            errorNamaStaff.setText("Nama tidak boleh kosong");
            return;
        } else {
            errorNamaStaff.setText(null);
        }

        // Validasi Email
        if (TextUtils.isEmpty(email)) {
            errorEmailStaff.setText("Email tidak boleh kosong");
            return;
        } else {
            errorEmailStaff.setText(null);
        }

        // Validasi Password
        if (TextUtils.isEmpty(password)) {
            errorPasswordStaff.setText("Password tidak boleh kosong");
            return;
        } else if (password.length() < 6) {
            errorPasswordStaff.setText("Isikan Password minimal 6 karakter");
            return;
        } else {
            errorPasswordStaff.setText(null);
        }

        // Validasi Konfirmasi Password
        if (TextUtils.isEmpty(rePassword)) {
            errorKonfirmasiPasswordStaff.setText("Konfirmasi password tidak boleh kosong");
            return;
        } else if (!password.equals(rePassword)) {
            errorKonfirmasiPasswordStaff.setText("Password tidak sama, silahkan coba lagi");
            mPassword.setText("");
            mKonfirmasiPassword.setText("");
            return;
        } else {
            errorKonfirmasiPasswordStaff.setText(null);
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