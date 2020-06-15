package com.covid19.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class DataActivity extends AppCompatActivity {

    TextView tvPositive, tvNegative, tvRecovered, tvDeath, tvTotal;
    String Positive, Negative, Recovered, Death, Total;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvPositive = findViewById(R.id.tvPositive);
        tvNegative = findViewById(R.id.tvNegative);
        tvRecovered = findViewById(R.id.tvRecovered);
        tvDeath = findViewById(R.id.tvDeath);
        tvTotal = findViewById(R.id.tvTotal);

        Positive = getIntent().getStringExtra("Positive");
        tvPositive.setText(String.format("Positive: %s", Positive));

        Negative = getIntent().getStringExtra("Negative");
        tvNegative.setText(String.format("Negative: %s", Negative));

        Recovered = getIntent().getStringExtra("Recovered");
        tvRecovered.setText(String.format("Recovered: %s", Recovered));

        Death = getIntent().getStringExtra("Death");
        tvDeath.setText(String.format("Death: %s", Death));

        Total = getIntent().getStringExtra("Total");
        tvTotal.setText(String.format("Total: %s", Total));


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, StateActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
