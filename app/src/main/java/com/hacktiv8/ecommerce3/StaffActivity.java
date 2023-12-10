package com.hacktiv8.ecommerce3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StaffActivity extends AppCompatActivity {
    private TextView mnamaLengkap, memail;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        mnamaLengkap = findViewById(R.id.nama_lengkap);
        memail = findViewById(R.id.email);
        ImageButton mlogoutBtn = findViewById(R.id.logoutBtn);
        mlogoutBtn.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(StaffActivity.this, WelcomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance("https://hacktiv8-ecommerce3-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String uid = user.getUid();
            readUserData(uid);
        }
    }

    private void readUserData(String uid) {
        mDatabase.child("staffs").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("name").getValue(String.class);
                String email = dataSnapshot.child("email").getValue(String.class);
                mnamaLengkap.setText(name);
                memail.setText(email);
                System.out.println(name);
                System.out.println(email);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle possible errors.
            }
        });
    }
}
