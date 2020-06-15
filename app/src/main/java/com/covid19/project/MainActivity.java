package com.covid19.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {


    CardView cardViewSymptoms, cardViewTreatment, cardViewAwareness, cardViewData;
    TextView tvUser;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvUser = findViewById(R.id.tvUser);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();


            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
            tvUser.setText(String.format("Welcome, %s", email));
        }

        cardViewSymptoms = findViewById(R.id.cardViewSymptoms);
        cardViewSymptoms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntentS = new Intent(MainActivity.this, Symptoms.class);
                startActivity(myIntentS);
            }
        });

        cardViewTreatment = findViewById(R.id.cardViewTreatment);
        cardViewTreatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntentS = new Intent(MainActivity.this, Treatment.class);
                startActivity(myIntentS);
            }
        });


        cardViewAwareness = findViewById(R.id.cardViewAwareness);
        cardViewAwareness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntentS = new Intent(MainActivity.this, Awareness.class);
                startActivity(myIntentS);
            }
        });


        cardViewData = findViewById(R.id.cardViewSearch);
        cardViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntentS = new Intent(MainActivity.this, StateActivity.class);
                startActivity(myIntentS);
            }
        });

        btnLogout=findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent myIntentS = new Intent(MainActivity.this, login.class);
                startActivity(myIntentS);
                finish();
            }
        });
    }


}