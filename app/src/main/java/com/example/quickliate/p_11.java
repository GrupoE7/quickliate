package com.example.quickliate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Models.Mensaje;
import Models.Mensaje2;
import adapter.MensajeAdapter;
import adapter.MensajeAdapter2;

public class p_11 extends AppCompatActivity {
    private MensajeAdapter madapter;

    private RecyclerView mrecyclerView;
    private ArrayList<Mensaje> mMensajeList = new ArrayList<>();
    private DatabaseReference mibase;
    private Button btbuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p11);
        mrecyclerView = (RecyclerView) findViewById(R.id.verSensore);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mibase=  FirebaseDatabase.getInstance().getReference();
        btbuscar= (Button) findViewById(R.id.Buscar);

        btbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(p_11.this, p_5.class);
                startActivity(i);

            }
        });








        mibase = FirebaseDatabase.getInstance().getReference();


        mibase=  FirebaseDatabase.getInstance().getReference();
        mibase .child("Nombre Sensor").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    mMensajeList.clear();
                    for(DataSnapshot ds:snapshot.getChildren()){
                        String cargo =ds.child("Gps").getValue().toString();//cargo//
                        String nombre =ds.child("Ubicacion").getValue().toString();//nombre//
                        String contrasena =" ";

                        mMensajeList.add(new  Mensaje(contrasena,cargo,nombre));
                    }
                    madapter = new MensajeAdapter(mMensajeList,R.layout.menasaje_ver);
                    mrecyclerView.setAdapter(madapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }});



        /////////////////////////////temp//////////////


        /////////////////////////////temp//////////////
    }
}