package com.hacktiv8.ecommerce3;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterUsersActivity extends AppCompatActivity {
    private EditText inputIdMember, inputNamaMember, inputEmailMember, inputPasswordMember1, inputPasswordMember2;
    private ImageButton bntSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_users);

        inputIdMember = findViewById(R.id.inputIdMember);
        inputNamaMember = findViewById(R.id.inputNamaMember);
        inputEmailMember = findViewById(R.id.inputEmailMember);
        inputPasswordMember1 = findViewById(R.id.inputPasswordMember1);
        inputPasswordMember2 = findViewById(R.id.inputPasswordMember2);

        bntSignUp = findViewById(R.id.bntSignUp);
    }
}