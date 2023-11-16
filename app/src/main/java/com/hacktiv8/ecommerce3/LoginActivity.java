package com.hacktiv8.ecommerce3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private String receivedScreen;
    private TextView descLogin;
    private EditText email, pw;
    ImageButton loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent intent = getIntent();
        if (intent != null) {
            receivedScreen = intent.getStringExtra("Activity");
        }
        descLogin = findViewById(R.id.desc_login);
        email = findViewById(R.id.email);
        pw = findViewById(R.id.pw);
        loginButton = findViewById(R.id.login);

        descLogin.setText(receivedScreen);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("Login Sebagai Admin".equals(receivedScreen)){
                    Intent intent = new Intent(v.getContext(), AdminActivity.class);
                    startActivity(intent);
                } else if ("Login Sebagai Staff".equals(receivedScreen)) {
                    Intent intent = new Intent(v.getContext(), StaffActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(v.getContext(), HomeUserActivity.class);
                    startActivity(intent);
                }
            }
        });


    }
}