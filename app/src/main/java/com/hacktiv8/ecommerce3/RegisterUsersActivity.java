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

public class RegisterUsersActivity extends AppCompatActivity {
    private EditText inputNamaMember;
    private EditText inputEmailMember;
    private EditText inputPasswordMember1;
    private EditText inputPasswordMember2;
    private FirebaseAuth mAuth;
    private TextView errorTextNama;
    private TextView errorTextEmail;
    private TextView errorTextPassword1;
    private TextView errorTextPassword2;
    private ProgressBar progressBarRegUser;

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

        errorTextNama = findViewById(R.id.errorTextNama);
        errorTextEmail = findViewById(R.id.errorTextEmail);
        errorTextPassword1 = findViewById(R.id.errorTextPassword1);
        errorTextPassword2 = findViewById(R.id.errorTextPassword2);

        progressBarRegUser = findViewById(R.id.progressBarRegUser);


        ImageButton bntSignUp = findViewById(R.id.bntSignUp);
        bntSignUp.setOnClickListener(v -> registerNewUser());

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                updateProgressBarRegUser();
            }
        };
        inputNamaMember.addTextChangedListener(textWatcher);
        inputEmailMember.addTextChangedListener(textWatcher);
        inputPasswordMember1.addTextChangedListener(textWatcher);
        inputPasswordMember2.addTextChangedListener(textWatcher);

    }

    private void updateProgressBarRegUser() {
        int filledEditTextCount = 0;
        if (!TextUtils.isEmpty(inputNamaMember.getText())) {
            filledEditTextCount++;
        }
        if (!TextUtils.isEmpty(inputEmailMember.getText())) {
            filledEditTextCount++;
        }
        if (!TextUtils.isEmpty(inputPasswordMember1.getText())) {
            filledEditTextCount++;
        }
        if (!TextUtils.isEmpty(inputPasswordMember2.getText())) {
            filledEditTextCount++;
        }
        int progress = filledEditTextCount * 25;
        progressBarRegUser.setProgress(progress);
    }

    private void registerNewUser() {
        String namaLengkap, email, password,rePassword;
        email = inputEmailMember.getText().toString();
        password = inputPasswordMember1.getText().toString();
        namaLengkap = inputNamaMember.getText().toString();
        rePassword = inputPasswordMember2.getText().toString();

        // Validasi Nama
        if (TextUtils.isEmpty(namaLengkap)) {
            errorTextNama.setText("Nama tidak boleh kosong");
            return;
        } else {
            errorTextNama.setText(null);
        }

        // Validasi Email
        if (TextUtils.isEmpty(email)) {
            errorTextEmail.setText("Email tidak boleh kosong");
            return;
        } else {
            errorTextEmail.setText(null);
        }

        // Validasi Password
        if (TextUtils.isEmpty(password)) {
            errorTextPassword1.setText("Password tidak boleh kosong");
            return;
        } else if (password.length() < 6) {
            errorTextPassword1.setText("Isikan Password minimal 6 karakter");
            return;
        } else {
            errorTextPassword1.setText(null);
        }

        // Validasi Konfirmasi Password
        if (TextUtils.isEmpty(rePassword)) {
            errorTextPassword2.setText("Konfirmasi password tidak boleh kosong");
            return;
        } else if (!password.equals(rePassword)) {
            errorTextPassword2.setText("Password tidak sama, silahkan coba lagi");
            inputPasswordMember1.setText("");
            inputPasswordMember2.setText("");
            return;
        } else {
            errorTextPassword2.setText(null);
        }

        Toast.makeText(getApplicationContext(), "Sedang Diproses", Toast.LENGTH_LONG).show();

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