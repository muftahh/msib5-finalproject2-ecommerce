package com.hacktiv8.ecommerce3;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton loginAdmin, loginMember, loginStaff;
    private TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        register = findViewById(R.id.register);
        loginAdmin = findViewById(R.id.login_admin);
        loginMember = findViewById(R.id.login_member);
        loginStaff = findViewById(R.id.login_staff);

        register.setOnClickListener(this);
        loginAdmin.setOnClickListener(this);
        loginMember.setOnClickListener(this);
        loginStaff.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.register) {
            register.setTextColor(Color.BLACK);
            Intent registerIntent = new Intent(this, RegisterUsersActivity.class);
            startActivity(registerIntent);
        } else if (v.getId() == R.id.login_admin) {
            Intent loginAdminIntent = new Intent(this, LoginActivity.class);
            String screen = "Login Sebagai Admin";
            loginAdminIntent.putExtra("Activity", screen);
            startActivity(loginAdminIntent);
        } else if (v.getId() == R.id.login_member) {
            Intent loginMemberIntent = new Intent(this, LoginActivity.class);
            String screen = "Login Sebagai Member";
            loginMemberIntent.putExtra("Activity", screen);
            startActivity(loginMemberIntent);
        } else if (v.getId() == R.id.login_staff) {
            Intent loginStaffIntent = new Intent(this, LoginActivity.class);
            String screen = "Login Sebagai Staff";
            loginStaffIntent.putExtra("Activity", screen);
            startActivity(loginStaffIntent);
        }
    }
}