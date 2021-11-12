package com.example.quickliate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Activity13 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_13);
    }

    public void irdatos(View view) {
        Intent a = new Intent(this, p_5.class);
        startActivity(a);
    }
    public void iralarm(View view) {
        Intent b = new Intent(this, p_5.class);
        startActivity(b);
    }
    public void iraconfi(View view) {
        Intent b = new Intent(this, p_5.class);
        startActivity(b);
    }

}