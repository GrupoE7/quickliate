package com.example.quickliate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Activity7 extends AppCompatActivity {
    private String sitioVer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_7);
        sitioVer = getIntent().getStringExtra("sitio");

    }
    public void usuarios(View view){
        Intent i = new Intent(this,p_8.class);
        startActivity(i);

    }
    public void datos(View view){
        Intent a = new Intent(this,p_5.class);
        startActivity(a);

    }
    public void alarma(View view){
        Intent b = new Intent(this,p_5.class);
        startActivity(b);

    }
    public void irconfigurasensor(View view){
        Intent c = new Intent(this,p_11.class);
        startActivity(c);

    }
    public void irubica(View view){
        Intent d = new Intent(this,MapsActivity.class);
        startActivity(d);

    }
    public void irconfigura(View view){
        Intent b = new Intent(this, activity_4.class);
        b.putExtra("id",sitioVer);
        startActivity(b);

    }
}